package com.alc.bookstore.shared.infrastructure.bus.event;

import com.alc.bookstore.shared.domain.annotation.EventBusComponent;
import com.alc.bookstore.shared.domain.bus.event.DomainEvent;
import com.alc.bookstore.shared.domain.bus.event.DomainEventSubscriber;
import com.alc.bookstore.shared.domain.bus.event.EventBus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Primary;

@EventBusComponent
@Primary
public class RabbitMQEventBus implements EventBus {

    private static final String MAIN_EXCHANGE = "backend.events";
    private static final String RETRY_EXCHANGE = "backend.events.retry";
    private static final String DLX_EXCHANGE = "backend.events.dlx";
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 5_000; // 5 s, adjust as needed

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final ConnectionFactory connectionFactory;
    private final Map<String, SimpleMessageListenerContainer> containers =
            new ConcurrentHashMap<>();

    public RabbitMQEventBus(
            final RabbitTemplate rabbitTemplate,
            final ObjectMapper objectMapper,
            final ConnectionFactory connectionFactory) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.connectionFactory = connectionFactory;
    }

    // -------------------------------------------------------------------------
    // Publish
    // -------------------------------------------------------------------------

    @Override
    public void publish(final List<DomainEvent> events) {
        events.forEach(
                event -> {
                    try {
                        String json = objectMapper.writeValueAsString(event);
                        String topic = event.getTopic();
                        rabbitTemplate.convertAndSend(MAIN_EXCHANGE, topic, json);
                    } catch (Exception e) {
                        throw new RuntimeException(
                                "Failed to publish event: " + event.getClass(), e);
                    }
                });
    }

    // -------------------------------------------------------------------------
    // Subscribe
    // -------------------------------------------------------------------------

    @Override
    public <T extends DomainEvent> void subscribe(
            final String topic, final DomainEventSubscriber<T> subscriber) {
        try {
            Channel adminChannel = connectionFactory.createConnection().createChannel(false);

            // Exchanges
            adminChannel.exchangeDeclare(MAIN_EXCHANGE, "topic", true);
            adminChannel.exchangeDeclare(RETRY_EXCHANGE, "topic", true);
            adminChannel.exchangeDeclare(DLX_EXCHANGE, "fanout", true);

            // 1. DLQ (Dead Letter Queue)
            String dlqName = topic + ".dlq";
            adminChannel.queueDeclare(dlqName, true, false, false, null);
            adminChannel.queueBind(dlqName, DLX_EXCHANGE, "");

            // 2. Main queue
            Map<String, Object> mainArgs = new HashMap<>();
            mainArgs.put("x-dead-letter-exchange", DLX_EXCHANGE);
            adminChannel.queueDeclare(topic, true, false, false, mainArgs);
            adminChannel.queueBind(topic, MAIN_EXCHANGE, topic);

            // 3. Retry queue (TTL + dead-letter back to main)
            String retryQueue = topic + ".retry";
            Map<String, Object> retryArgs = new HashMap<>();
            retryArgs.put("x-dead-letter-exchange", MAIN_EXCHANGE);
            retryArgs.put("x-dead-letter-routing-key", topic);
            retryArgs.put("x-message-ttl", RETRY_DELAY_MS);
            adminChannel.queueDeclare(retryQueue, true, false, false, retryArgs);
            adminChannel.queueBind(retryQueue, RETRY_EXCHANGE, topic);

            adminChannel.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to declare queue before subscribing: " + topic, e);
        }

        // 4️.- Listener container
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(topic);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(
                (ChannelAwareMessageListener)
                        (message, channel) -> {
                            String body = new String(message.getBody());
                            long deliveryTag = message.getMessageProperties().getDeliveryTag();

                            try {
                                Class<T> eventType = extractSubscribedEventClass(subscriber);
                                T event = objectMapper.readValue(body, eventType);
                                subscriber.consume(event);

                                // ACK on success
                                channel.basicAck(deliveryTag, false);

                            } catch (Exception processingError) {
                                // Retry / DLX management
                                Map<String, Object> headers =
                                        message.getMessageProperties().getHeaders();
                                int retryCount =
                                        headers.getOrDefault("x-retries", 0) instanceof Integer
                                                ? (Integer) headers.get("x-retries")
                                                : 0;

                                if (retryCount >= MAX_RETRIES) {
                                    // Publish manually into DLX queue
                                    rabbitTemplate.convertAndSend(
                                            DLX_EXCHANGE,
                                            "", // fanout due to empty rounting key
                                            body,
                                            m -> {
                                                m.getMessageProperties()
                                                        .getHeaders()
                                                        .put("x-retries", retryCount);
                                                return m;
                                            });

                                    // ACK el mensaje original (ya lo mandamos a DLQ)
                                    channel.basicAck(deliveryTag, false);
                                    System.err.printf(
                                            "[RabbitMQEventBus] Moved to DLQ after %d retries: %s%n",
                                            retryCount, body);
                                } else {
                                    // Publish to retry exchange with incremented retry header
                                    int nextRetry = retryCount + 1;
                                    rabbitTemplate.convertAndSend(
                                            RETRY_EXCHANGE,
                                            topic,
                                            body,
                                            m -> {
                                                m.getMessageProperties()
                                                        .getHeaders()
                                                        .put("x-retries", nextRetry);
                                                return m;
                                            });

                                    // ACK current (already re‑published)
                                    channel.basicAck(deliveryTag, false);
                                    System.err.printf(
                                            "[RabbitMQEventBus] Retry %d/%d scheduled for event: %s%n",
                                            nextRetry, MAX_RETRIES, body);
                                }
                            }
                        });

        container.start();
        containers.put(topic, container);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    private <T extends DomainEvent> Class<T> extractSubscribedEventClass(
            final DomainEventSubscriber<T> subscriber) {
        ParameterizedType type =
                (ParameterizedType) subscriber.getClass().getGenericInterfaces()[0];
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}

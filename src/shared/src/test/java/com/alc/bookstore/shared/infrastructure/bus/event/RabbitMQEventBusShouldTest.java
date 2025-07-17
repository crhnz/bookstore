package com.alc.bookstore.shared.infrastructure.bus.event;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alc.bookstore.shared.domain.Utils;
import com.alc.bookstore.shared.domain.annotation.SubscriberComponent;
import com.alc.bookstore.shared.domain.bus.event.DomainEvent;
import com.alc.bookstore.shared.domain.bus.event.DomainEventSubscriber;
import com.alc.bookstore.shared.infrastructure.SpringBootInfrastructureTestCase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RabbitMQEventBusShouldTest extends SpringBootInfrastructureTestCase {

    private static final String topicTest = "com.test";
    private static final String failingTopicTest = "com.test.fail";

    @Autowired private RabbitMQEventBus eventBus;

    @Autowired private TestSubscriber subscriber;

    @Autowired private FailingSubscriber failingSubscriber;

    @BeforeAll
    protected void beforeAll() {
        eventBus.subscribe(topicTest, subscriber);
        eventBus.subscribe(failingTopicTest, failingSubscriber);
    }

    @BeforeEach
    protected void setUp() {
        subscriber.hasBeenExecuted = false;
        failingSubscriber.hasBeenCalled = false;
    }

    @Test
    void publish_and_consume_domain_events_from_rabbitmq() throws Exception {
        EventTest domainEvent = new EventTest("Hi there!", ZonedDateTime.now());

        eventBus.publish(Collections.singletonList(domainEvent));

        eventually(() -> assertTrue(subscriber.hasBeenExecuted));
    }

    @Test
    void it_should_retry_if_subscriber_throws() throws Exception {
        FailureEventTest event = new FailureEventTest("Retry me!", ZonedDateTime.now());

        eventBus.publish(Collections.singletonList(event));

        eventually(() -> assertTrue(failingSubscriber.hasBeenCalled));
    }

    @EqualsAndHashCode(callSuper = true)
    public static final class EventTest extends DomainEvent {

        private final String message;

        @JsonCreator
        public EventTest(
                @JsonProperty("message") final String message,
                @JsonProperty("createdAt") final ZonedDateTime createdAt) {
            super(topicTest, Utils.dateToString(createdAt));
            this.message = message;
        }
    }

    @SubscriberComponent
    public static final class TestSubscriber implements DomainEventSubscriber<EventTest> {
        public Boolean hasBeenExecuted = false;

        @Override
        public void consume(EventTest event) {
            hasBeenExecuted = true;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    public static final class FailureEventTest extends DomainEvent {

        private final String message;

        @JsonCreator
        public FailureEventTest(
                @JsonProperty("message") final String message,
                @JsonProperty("createdAt") final ZonedDateTime createdAt) {
            super(failingTopicTest, Utils.dateToString(createdAt));
            this.message = message;
        }
    }

    @SubscriberComponent
    public static final class FailingSubscriber implements DomainEventSubscriber<EventTest> {
        public boolean hasBeenCalled = false;

        @Override
        public void consume(EventTest event) {
            hasBeenCalled = true;
            throw new RuntimeException("Fail on purpose");
        }
    }
}

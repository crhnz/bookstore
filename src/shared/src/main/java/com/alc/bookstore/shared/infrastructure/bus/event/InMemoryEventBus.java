package com.alc.bookstore.shared.infrastructure.bus.event;

import com.alc.bookstore.shared.domain.annotation.EventBusComponent;
import com.alc.bookstore.shared.domain.bus.event.DomainEvent;
import com.alc.bookstore.shared.domain.bus.event.DomainEventSubscriber;
import com.alc.bookstore.shared.domain.bus.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EventBusComponent
public final class InMemoryEventBus implements EventBus {

    private final Map<String, List<DomainEventSubscriber<?>>> subscribers = new HashMap<>();
    private final ExecutorService executor;

    public InMemoryEventBus() {
        // TODO harcoded value
        this.executor = Executors.newFixedThreadPool(30);
    }

    @Override
    public void publish(final List<DomainEvent> events) {
        for (DomainEvent event : events) {
            List<DomainEventSubscriber<?>> eventSubscribers =
                    subscribers.getOrDefault(event.getTopic(), List.of());
            for (DomainEventSubscriber<?> subscriber : eventSubscribers) {
                executor.submit(() -> invokeSubscriber(subscriber, event));
            }
        }
    }

    @Override
    public <T extends DomainEvent> void subscribe(
            final String topic, final DomainEventSubscriber<T> subscriber) {
        subscribers.computeIfAbsent(topic, k -> new ArrayList<>()).add(subscriber);
    }

    @SuppressWarnings("unchecked")
    private <T extends DomainEvent> void invokeSubscriber(
            final DomainEventSubscriber<?> subscriber, final T event) {
        ((DomainEventSubscriber<T>) subscriber).consume(event);
    }
}

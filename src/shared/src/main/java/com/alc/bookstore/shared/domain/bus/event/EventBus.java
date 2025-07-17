package com.alc.bookstore.shared.domain.bus.event;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);

    <T extends DomainEvent> void subscribe(
            final String topic, final DomainEventSubscriber<T> subscriber);
}

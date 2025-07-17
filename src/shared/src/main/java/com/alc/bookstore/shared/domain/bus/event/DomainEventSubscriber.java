package com.alc.bookstore.shared.domain.bus.event;

@FunctionalInterface
public interface DomainEventSubscriber<T extends DomainEvent> {
    void consume(T event);
}

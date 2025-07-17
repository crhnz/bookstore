package com.alc.bookstore.shared.domain.bus.event;

import java.util.UUID;
import lombok.Getter;

@Getter
public abstract class DomainEvent {
    private final String processId;
    private final String eventId;
    private final String createdAt;
    private final String topic;

    protected DomainEvent(final String processId, final String createdAt, final String topic) {
        this.processId = processId;
        this.eventId = UUID.randomUUID().toString();
        this.createdAt = createdAt;
        this.topic = topic;
    }

    protected DomainEvent(final String topic, final String createdAt) {
        this.eventId = UUID.randomUUID().toString();
        this.processId = eventId;
        this.createdAt = createdAt;
        this.topic = topic;
    }
}

package com.alc.bookstore.shared.domain;

import com.alc.bookstore.shared.domain.bus.event.DomainEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {
    private List<DomainEvent> domainEvents = new ArrayList<>();

    public final List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = domainEvents;

        domainEvents = Collections.emptyList();

        return events;
    }

    protected final void register(final DomainEvent event) {
        domainEvents.add(event);
    }
}

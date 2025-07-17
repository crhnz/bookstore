package com.alc.bookstore.shared.application;

import static org.mockito.Mockito.*;

import com.alc.bookstore.shared.domain.bus.event.DomainEvent;
import com.alc.bookstore.shared.domain.bus.event.EventBus;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

public abstract class UnitTestCase {
    protected EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        eventBus = mock(EventBus.class);
    }

    public void shouldHavePublished(List<DomainEvent> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(argThat(domainEvents::equals));
    }

    public void shouldHavePublished(DomainEvent domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }
}

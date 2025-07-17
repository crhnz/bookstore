package com.alc.bookstore.shared.infrastructure.bus.event;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alc.bookstore.shared.domain.UUIDMother;
import com.alc.bookstore.shared.domain.Utils;
import com.alc.bookstore.shared.domain.bus.event.DomainEvent;
import com.alc.bookstore.shared.domain.bus.event.DomainEventSubscriber;
import com.alc.bookstore.shared.infrastructure.IntegrationTestCase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

public final class InMemoryEventBusShouldTest extends IntegrationTestCase {

    private final InMemoryEventBus eventBus = new InMemoryEventBus();

    @Test
    void it_should_dispatch_events_to_subscribed_handlers_asynchronously() throws Exception {
        DummyEvent event =
                new DummyEvent(UUIDMother.random(), ZonedDateTime.now(), UUIDMother.random());
        AtomicBoolean consumed = new AtomicBoolean(false);

        DomainEventSubscriber<DummyEvent> subscriber = (final DummyEvent e) -> consumed.set(true);

        eventBus.subscribe(event.getTopic(), subscriber);

        eventBus.publish(List.of(event));

        eventually(() -> assertTrue(consumed.get()));
    }

    // Dummy Event
    @EqualsAndHashCode(callSuper = true)
    private static final class DummyEvent extends DomainEvent {
        private final String id;

        @JsonCreator
        public DummyEvent(
                @JsonProperty("processId") final String processId,
                @JsonProperty("createdAt") final ZonedDateTime createdAt,
                @JsonProperty("id") final String id) {
            super(processId, "dummy.event", Utils.dateToString(createdAt));
            this.id = id;
        }

        // Helper to simulate a lambda that sets true
        private interface FlagSetter extends DomainEventSubscriber<DummyEvent> {
            @Override
            default void consume(DummyEvent event) {
                setTrue();
            }

            void setTrue();
        }
    }
}

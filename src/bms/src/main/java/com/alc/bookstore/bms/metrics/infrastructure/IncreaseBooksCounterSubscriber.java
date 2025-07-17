package com.alc.bookstore.bms.metrics.infrastructure;

import com.alc.bookstore.bms.metrics.application.books.counter.IncreaseBooksCounterCommand;
import com.alc.bookstore.bms.metrics.application.books.counter.IncreaseBooksCounterCommandHandler;
import com.alc.bookstore.shared.domain.annotation.SubscriberComponent;
import com.alc.bookstore.shared.domain.book.CreatedBookDomainEvent;
import com.alc.bookstore.shared.domain.bus.event.DomainEventSubscriber;
import lombok.AllArgsConstructor;

@SubscriberComponent
@AllArgsConstructor
public final class IncreaseBooksCounterSubscriber
        implements DomainEventSubscriber<CreatedBookDomainEvent> {

    private IncreaseBooksCounterCommandHandler handler;

    @Override
    public void consume(final CreatedBookDomainEvent event) {

        // Call UC
        handler.handle(new IncreaseBooksCounterCommand());
    }
}

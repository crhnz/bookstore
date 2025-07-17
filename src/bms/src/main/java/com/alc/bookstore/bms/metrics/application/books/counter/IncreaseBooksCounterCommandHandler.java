package com.alc.bookstore.bms.metrics.application.books.counter;

import com.alc.bookstore.shared.domain.annotation.CommandBusComponent;
import com.alc.bookstore.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@CommandBusComponent
public final class IncreaseBooksCounterCommandHandler
        implements CommandHandler<IncreaseBooksCounterCommand> {

    private final IncreaseBooksCounterUC uc;

    @Override
    public void handle(final IncreaseBooksCounterCommand command) {

        // Call the Use Case
        uc.invoke();
    }
}

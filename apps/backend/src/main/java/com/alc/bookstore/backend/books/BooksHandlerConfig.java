package com.alc.bookstore.backend.books;

import com.alc.bookstore.bms.books.application.create.CreateBookCommand;
import com.alc.bookstore.bms.books.application.create.CreateBookCommandHandler;
import com.alc.bookstore.bms.books.application.findall.FindAllBooksQuery;
import com.alc.bookstore.bms.books.application.findall.FindAllBooksQueryHandler;
import com.alc.bookstore.shared.domain.bus.command.CommandBus;
import com.alc.bookstore.shared.domain.bus.query.QueryBus;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@AllArgsConstructor
public class BooksHandlerConfig {

    private final QueryBus queryBus;
    private final CommandBus commandBus;
    private final FindAllBooksQueryHandler findAllBooksHandler;
    private final CreateBookCommandHandler createBookCommandHandler;

    @EventListener(ApplicationReadyEvent.class)
    public void register() {

        // Register query handlers
        queryBus.registerHandler(FindAllBooksQuery.class, findAllBooksHandler);

        commandBus.registerHandler(CreateBookCommand.class, createBookCommandHandler);
    }
}

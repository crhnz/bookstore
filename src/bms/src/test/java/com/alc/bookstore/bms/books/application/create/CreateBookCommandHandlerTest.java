package com.alc.bookstore.bms.books.application.create;

import com.alc.bookstore.bms.books.BooksModuleUnitTest;
import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.bms.books.domain.BookMother;
import com.alc.bookstore.bms.books.domain.CreatedBookDomainEventMother;
import com.alc.bookstore.shared.domain.book.CreatedBookDomainEvent;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateBookCommandHandlerTest extends BooksModuleUnitTest {
    private CreateBookCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new CreateBookCommandHandler(new CreateBookUC(repository, eventBus));
    }

    @Test
    void create_a_valid_course() {
        // ARRANGE
        CreateBookCommand command = CreateBookCommandMother.random(ZonedDateTime.now());
        Book book = BookMother.fromRequest(command);
        CreatedBookDomainEvent domainEvent =
                CreatedBookDomainEventMother.fromBook(command.getOccurredOn(), book);

        // ACT
        handler.handle(command);

        // ASSERT
        shouldHaveSaved(book);
        // TODO shouldHavePublished(domainEvent);
    }
}

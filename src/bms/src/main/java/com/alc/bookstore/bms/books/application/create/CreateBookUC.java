package com.alc.bookstore.bms.books.application.create;

import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.bms.books.domain.BookRepository;
import com.alc.bookstore.bms.books.domain.vo.BookDescription;
import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.bms.books.domain.vo.BookTitle;
import com.alc.bookstore.shared.domain.annotation.UseCaseComponent;
import com.alc.bookstore.shared.domain.bus.event.EventBus;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCaseComponent
public class CreateBookUC {

    private final BookRepository repository;
    private final EventBus eventBus;

    public void invoke(
            final BookID id,
            final BookTitle title,
            final BookDescription description,
            final BookGenre genre,
            final ZonedDateTime occurredOn) {
        // Create the book
        Book book = Book.createBook(id, title, description, genre, occurredOn);

        repository.save(book);

        eventBus.publish(book.pullDomainEvents());
    }
}

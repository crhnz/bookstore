package com.alc.bookstore.bms.books.domain;

import com.alc.bookstore.bms.books.domain.vo.BookDescription;
import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.bms.books.domain.vo.BookTitle;
import com.alc.bookstore.shared.domain.AggregateRoot;
import com.alc.bookstore.shared.domain.book.CreatedBookDomainEvent;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@SuppressWarnings("PMD.ShortClassName")
public final class Book extends AggregateRoot {

    private BookID id;

    private BookTitle title;

    private BookDescription description;

    private BookGenre gender;

    public static Book createBook(
            final BookID id,
            final BookTitle title,
            final BookDescription description,
            final BookGenre genre,
            final ZonedDateTime createdAt) {

        Book book = new Book(id, title, description, genre);

        book.register(
                new CreatedBookDomainEvent(
                        createdAt,
                        id.getValue(),
                        title.getValue(),
                        description.getValue(),
                        genre.getValue()));

        return book;
    }
}

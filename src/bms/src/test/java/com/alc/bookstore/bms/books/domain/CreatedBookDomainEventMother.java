package com.alc.bookstore.bms.books.domain;

import com.alc.bookstore.bms.books.domain.vo.BookDescription;
import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.bms.books.domain.vo.BookTitle;
import com.alc.bookstore.shared.domain.book.CreatedBookDomainEvent;
import java.time.ZonedDateTime;

public final class CreatedBookDomainEventMother {
    public static CreatedBookDomainEvent create(
            final ZonedDateTime createdAt,
            final BookID id,
            final BookTitle title,
            final BookDescription description,
            final BookGenre genre) {
        return new CreatedBookDomainEvent(
                createdAt,
                id.getValue(),
                title.getValue(),
                description.getValue(),
                genre.getValue());
    }

    public static CreatedBookDomainEvent fromBook(final ZonedDateTime createdAt, final Book book) {
        return create(
                createdAt, book.getId(), book.getTitle(), book.getDescription(), book.getGender());
    }

    public static CreatedBookDomainEvent random(final ZonedDateTime createdAt) {
        return create(
                createdAt,
                BookIDMother.random(),
                BookTitleMother.random(),
                BookDescriptionMother.random(),
                BookGenreMother.random());
    }
}

package com.alc.bookstore.bms.books.domain;

import com.alc.bookstore.bms.books.application.create.CreateBookCommand;
import com.alc.bookstore.bms.books.domain.vo.BookDescription;
import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.bms.books.domain.vo.BookTitle;

public final class BookMother {
    public static Book create(
            final BookID id,
            final BookTitle title,
            final BookDescription description,
            final BookGenre genre) {
        return new Book(id, title, description, genre);
    }

    public static Book fromRequest(CreateBookCommand request) {
        return create(
                BookIDMother.create(request.getId()),
                BookTitleMother.create(request.getTitle()),
                BookDescriptionMother.create(request.getDescription()),
                BookGenreMother.create(request.getGenre()));
    }

    public static Book random() {
        return create(
                BookIDMother.random(),
                BookTitleMother.random(),
                BookDescriptionMother.random(),
                BookGenreMother.random());
    }
}

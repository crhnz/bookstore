package com.alc.bookstore.bms.books.application;

import com.alc.bookstore.bms.books.domain.BookDescriptionMother;
import com.alc.bookstore.bms.books.domain.BookGenreMother;
import com.alc.bookstore.bms.books.domain.BookIDMother;
import com.alc.bookstore.bms.books.domain.BookTitleMother;
import com.alc.bookstore.bms.books.domain.vo.BookDescription;
import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.bms.books.domain.vo.BookTitle;

public class BookResponseMother {
    public static BookResponse create(
            final BookID id,
            final BookTitle title,
            final BookDescription description,
            final BookGenre genre) {
        return new BookResponse(
                id.getValue(), title.getValue(), description.getValue(), genre.getValue());
    }

    public static BookResponse random() {
        return create(
                BookIDMother.random(),
                BookTitleMother.random(),
                BookDescriptionMother.random(),
                BookGenreMother.random());
    }
}

package com.alc.bookstore.bms.books.domain;

import com.alc.bookstore.bms.books.domain.vo.BookTitle;
import com.alc.bookstore.shared.domain.WordMother;

public class BookTitleMother {
    public static BookTitle create(String value) {
        return new BookTitle(value);
    }

    public static BookTitle random() {
        return create(WordMother.random());
    }
}

package com.alc.bookstore.bms.books.domain;

import com.alc.bookstore.bms.books.domain.vo.BookDescription;
import com.alc.bookstore.shared.domain.WordMother;

public class BookDescriptionMother {
    public static BookDescription create(String value) {
        return new BookDescription(value);
    }

    public static BookDescription random() {
        return create(WordMother.random());
    }
}

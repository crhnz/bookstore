package com.alc.bookstore.bms.books.domain;

import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.shared.domain.UUIDMother;

public class BookIDMother {
    public static BookID create(String value) {
        return new BookID(value);
    }

    public static BookID random() {
        return create(UUIDMother.random());
    }
}

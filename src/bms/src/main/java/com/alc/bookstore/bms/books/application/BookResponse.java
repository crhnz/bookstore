package com.alc.bookstore.bms.books.application;

import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.shared.domain.bus.query.Response;

public record BookResponse(String id, String title, String description, String gender)
        implements Response {

    public static BookResponse fromAggregate(final Book book) {
        return new BookResponse(
                book.getId().getValue(),
                book.getTitle().getValue(),
                book.getDescription().getValue(),
                book.getGender().getValue());
    }
}

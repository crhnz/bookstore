package com.alc.bookstore.bms.books.domain.exceptions;

import com.alc.bookstore.shared.domain.DomainError;

public final class BookNotFoundException extends DomainError {

    private final String bookId;

    public BookNotFoundException(final String bookId) {
        super("book_not_found", String.format("Book with ID <%s> not found", bookId));
        this.bookId = bookId;
    }

    public String bookId() {
        return bookId;
    }
}

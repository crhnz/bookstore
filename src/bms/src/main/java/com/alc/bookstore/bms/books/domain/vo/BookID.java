package com.alc.bookstore.bms.books.domain.vo;

import com.alc.bookstore.shared.domain.vo.Identifier;
import java.util.Optional;

public final class BookID extends Identifier {
    public BookID(final String value) {
        super(value);
    }

    public static BookID of(final String value) {
        return new BookID(value);
    }

    public static Optional<BookID> ofNullable(final String value) {
        return Optional.ofNullable(value).map(BookID::new);
    }
}

package com.alc.bookstore.bms.books.domain.vo;

import com.alc.bookstore.shared.domain.vo.StringValueObject;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookDescription extends StringValueObject {
    public BookDescription(final String value) {
        super(value);

        ensureValidDescription(value);
    }

    private void ensureValidDescription(final String value) throws IllegalArgumentException {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Description must not be null or blank");
        }
    }

    public static BookDescription of(final String value) {
        return new BookDescription(value);
    }

    public static Optional<BookDescription> ofNullable(final String value) {
        return Optional.ofNullable(value).map(BookDescription::new);
    }
}

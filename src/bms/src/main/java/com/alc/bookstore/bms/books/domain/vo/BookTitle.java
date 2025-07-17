package com.alc.bookstore.bms.books.domain.vo;

import com.alc.bookstore.shared.domain.vo.StringValueObject;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class BookTitle extends StringValueObject {

    public BookTitle(final String value) {
        super(value);

        this.ensureValidTitle(value);
    }

    private void ensureValidTitle(final String value) throws IllegalArgumentException {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank");
        }
    }

    public static BookTitle of(final String value) {
        return new BookTitle(value);
    }

    public static Optional<BookTitle> ofNullable(final String value) {
        return Optional.ofNullable(value).map(BookTitle::new);
    }
}

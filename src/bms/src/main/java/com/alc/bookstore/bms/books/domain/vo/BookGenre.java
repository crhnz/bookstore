package com.alc.bookstore.bms.books.domain.vo;

import com.alc.bookstore.shared.domain.vo.StringValueObject;
import java.util.Optional;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class BookGenre extends StringValueObject {
    private static final Set<String> ALLOWED_GENRES =
            Set.of("Fantasy", "Science Fiction", "Romance", "Mystery", "Thriller", "Non-fiction");

    public BookGenre(final String value) {
        super(value);

        this.ensureValidGenre(value);
    }

    private void ensureValidGenre(final String value) throws IllegalArgumentException {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Genre must not be null or blank");
        }
        if (!ALLOWED_GENRES.contains(value)) {
            throw new IllegalArgumentException("Invalid genre: " + value);
        }
    }

    public static BookGenre of(final String value) {
        return new BookGenre(value);
    }

    public static Optional<BookGenre> ofNullable(final String value) {
        return Optional.ofNullable(value).map(BookGenre::new);
    }

    public static Set<String> allowedGenres() {
        return ALLOWED_GENRES;
    }
}

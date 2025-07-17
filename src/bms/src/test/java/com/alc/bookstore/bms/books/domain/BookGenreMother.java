package com.alc.bookstore.bms.books.domain;

import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.shared.domain.IntegerMother;
import java.util.List;

public final class BookGenreMother {

    public static BookGenre random() {
        String genre = randomGenre();
        return BookGenre.of(genre);
    }

    public static String randomGenre() {
        List<String> allowedGenres = List.copyOf(BookGenre.allowedGenres());
        int index = IntegerMother.random(allowedGenres.size());
        return allowedGenres.get(index);
    }

    public static BookGenre create(String value) {
        return BookGenre.of(value);
    }
}

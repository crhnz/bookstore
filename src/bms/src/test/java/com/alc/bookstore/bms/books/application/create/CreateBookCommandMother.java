package com.alc.bookstore.bms.books.application.create;

import com.alc.bookstore.bms.books.domain.BookGenreMother;
import com.alc.bookstore.shared.domain.UUIDMother;
import com.alc.bookstore.shared.domain.WordMother;
import java.time.ZonedDateTime;

public final class CreateBookCommandMother {

    public static CreateBookCommand create(
            ZonedDateTime occurredOn, String id, String title, String description, String genre) {
        return new CreateBookCommand(occurredOn, id, title, description, genre);
    }

    public static CreateBookCommand random(ZonedDateTime occurredOn) {
        return new CreateBookCommand(
                occurredOn,
                UUIDMother.random(),
                WordMother.random(),
                WordMother.random(),
                BookGenreMother.randomGenre());
    }
}

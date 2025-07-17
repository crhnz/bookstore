package com.alc.bookstore.bms.books.application.create;

import com.alc.bookstore.shared.domain.bus.command.Command;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class CreateBookCommand implements Command {
    private ZonedDateTime occurredOn;
    private String id;
    private String title;
    private String description;
    private String genre;
}

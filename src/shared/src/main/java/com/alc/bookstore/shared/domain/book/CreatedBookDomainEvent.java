package com.alc.bookstore.shared.domain.book;

import com.alc.bookstore.shared.domain.Utils;
import com.alc.bookstore.shared.domain.bus.event.DomainEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CreatedBookDomainEvent extends DomainEvent {

    private final String bookId;

    private final String title;

    private final String description;

    private final String genre;

    @JsonCreator
    public CreatedBookDomainEvent(
            @JsonProperty("createdAt") final ZonedDateTime createdAt,
            @JsonProperty("bookId") final String bookId,
            @JsonProperty("title") final String title,
            @JsonProperty("description") final String description,
            @JsonProperty("genre") final String genre) {
        super(Constants.createdBookTopic, Utils.dateToString(createdAt));
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.genre = genre;
    }
}

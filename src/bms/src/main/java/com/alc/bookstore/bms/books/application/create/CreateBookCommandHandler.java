package com.alc.bookstore.bms.books.application.create;

import com.alc.bookstore.bms.books.domain.vo.BookDescription;
import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.bms.books.domain.vo.BookTitle;
import com.alc.bookstore.shared.domain.annotation.CommandHandlerComponent;
import com.alc.bookstore.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@CommandHandlerComponent
public final class CreateBookCommandHandler implements CommandHandler<CreateBookCommand> {

    private final CreateBookUC uc;

    @Override
    public void handle(final CreateBookCommand command) {
        // Prepare and validate the input parameters
        BookID id = BookID.of(command.getId());
        BookDescription description = BookDescription.of(command.getDescription());
        BookTitle title = BookTitle.of(command.getTitle());
        BookGenre genre = BookGenre.of(command.getGenre());

        // Call the Use Case
        uc.invoke(id, title, description, genre, command.getOccurredOn());
    }
}

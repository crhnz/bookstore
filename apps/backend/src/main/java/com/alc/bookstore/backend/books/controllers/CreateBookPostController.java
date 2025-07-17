package com.alc.bookstore.backend.books.controllers;

import com.alc.bookstore.backend.spring.APIController;
import com.alc.bookstore.bms.books.application.create.CreateBookCommand;
import com.alc.bookstore.shared.domain.DomainError;
import com.alc.bookstore.shared.domain.bus.command.CommandBus;
import com.alc.bookstore.shared.domain.bus.query.QueryBus;
import java.time.ZonedDateTime;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books2")
// TODO review books2 in url
public final class CreateBookPostController extends APIController {

    public CreateBookPostController(final QueryBus queryBus, final CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping
    public ResponseEntity<String> create(final @RequestBody CreateBookCommand request) {
        // Assign now as occurredOn timestamp
        request.setOccurredOn(ZonedDateTime.now());

        // Dispatch the command
        dispatch(request);

        // Answer using HTTP format
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}

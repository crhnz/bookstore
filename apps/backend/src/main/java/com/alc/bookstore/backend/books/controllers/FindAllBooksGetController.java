package com.alc.bookstore.backend.books.controllers;

import com.alc.bookstore.backend.spring.APIController;
import com.alc.bookstore.bms.books.application.BookResponse;
import com.alc.bookstore.bms.books.application.findall.FindAllBooksQuery;
import com.alc.bookstore.shared.application.PaginatedResponse;
import com.alc.bookstore.shared.domain.DomainError;
import com.alc.bookstore.shared.domain.bus.command.CommandBus;
import com.alc.bookstore.shared.domain.bus.query.QueryBus;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public final class FindAllBooksGetController extends APIController {

    public FindAllBooksGetController(final QueryBus queryBus, final CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @Override
    public Map<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>();
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<BookResponse>> findAll(
            final @RequestParam(required = false) String title,
            final @RequestParam(required = false) String genre,
            final @RequestParam(required = false) String orderBy,
            final @RequestParam(required = false) String orderType,
            final @RequestParam(required = false) Integer offset,
            final @RequestParam(required = false) Integer limit) {
        FindAllBooksQuery query =
                new FindAllBooksQuery(title, genre, orderBy, orderType, offset, limit);

        PaginatedResponse<BookResponse> result = ask(query);
        return ResponseEntity.ok(result);
    }
}

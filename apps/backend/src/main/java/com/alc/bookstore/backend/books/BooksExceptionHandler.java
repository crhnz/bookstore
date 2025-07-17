package com.alc.bookstore.backend.books;

import com.alc.bookstore.backend.spring.BaseExceptionHandler;
import com.alc.bookstore.bms.books.domain.exceptions.BookNotFoundException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(
        basePackages = {
            "com.alc.bookstore.bms.books",
            "com.alc.bookstore.backend.books",
        })
public final class BooksExceptionHandler extends BaseExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handle(final BookNotFoundException e) {
        return errorResponse(e, HttpStatus.NOT_FOUND);
    }
}

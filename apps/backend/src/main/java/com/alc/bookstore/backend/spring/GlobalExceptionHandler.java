package com.alc.bookstore.backend.spring;

import com.alc.bookstore.shared.domain.DomainError;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(DomainError.class)
    public ResponseEntity<Map<String, String>> handle(final DomainError error) {
        return errorResponse(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

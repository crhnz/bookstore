package com.alc.bookstore.backend.spring;

import com.alc.bookstore.shared.domain.DomainError;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseExceptionHandler {

    protected ResponseEntity<Map<String, String>> errorResponse(
            final DomainError error, final HttpStatus status) {
        return ResponseEntity.status(status)
                .body(
                        Map.of(
                                "error_code", error.errorCode(),
                                "message", error.errorMessage()));
    }
}

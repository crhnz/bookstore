package com.alc.bookstore.bms.metrics.application.books.counter;

import com.alc.bookstore.bms.metrics.domain.MetricRepository;
import com.alc.bookstore.shared.domain.annotation.UseCaseComponent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCaseComponent
public class IncreaseBooksCounterUC {

    private final MetricRepository repository;

    public void invoke() {
        // Look for the repository
        repository.increaseBooksCounter();
    }
}

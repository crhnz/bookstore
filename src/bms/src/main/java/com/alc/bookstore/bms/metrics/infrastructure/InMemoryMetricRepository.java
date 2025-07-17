package com.alc.bookstore.bms.metrics.infrastructure;

import com.alc.bookstore.bms.metrics.domain.Metric;
import com.alc.bookstore.bms.metrics.domain.MetricRepository;
import com.alc.bookstore.bms.metrics.domain.vo.BooksCounter;
import com.alc.bookstore.shared.domain.annotation.RepositoryComponent;

@RepositoryComponent
public final class InMemoryMetricRepository implements MetricRepository {
    Metric metric = Metric.create(BooksCounter.of(0));

    public void increaseBooksCounter() {
        metric.getBooks()
                .setCounter(BooksCounter.of(metric.getBooks().getCounter().getValue() + 1));
    }

    @Override
    public Metric search() {
        return metric;
    }
}

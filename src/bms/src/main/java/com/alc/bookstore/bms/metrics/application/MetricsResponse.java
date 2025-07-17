package com.alc.bookstore.bms.metrics.application;

import com.alc.bookstore.bms.metrics.domain.Metric;
import com.alc.bookstore.shared.domain.bus.query.Response;

public record MetricsResponse(BookMetricsResponse books) implements Response {

    record BookMetricsResponse(Integer counter) {}

    public static MetricsResponse fromAggregate(final Metric metric) {
        return new MetricsResponse(
                new BookMetricsResponse(metric.getBooks().getCounter().getValue()));
    }
}

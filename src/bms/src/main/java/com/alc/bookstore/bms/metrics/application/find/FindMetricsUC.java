package com.alc.bookstore.bms.metrics.application.find;

import com.alc.bookstore.bms.metrics.application.MetricsResponse;
import com.alc.bookstore.bms.metrics.domain.Metric;
import com.alc.bookstore.bms.metrics.domain.MetricRepository;
import com.alc.bookstore.shared.domain.annotation.UseCaseComponent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCaseComponent
public class FindMetricsUC {

    private final MetricRepository repository;

    public MetricsResponse invoke() {
        // Look for the repository
        Metric metric = repository.search();

        // Return the response
        return MetricsResponse.fromAggregate(metric);
    }
}

package com.alc.bookstore.bms.metrics.application.find;

import com.alc.bookstore.bms.metrics.application.MetricsResponse;
import com.alc.bookstore.shared.domain.annotation.QueryHandlerComponent;
import com.alc.bookstore.shared.domain.bus.query.QueryHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@QueryHandlerComponent
public final class FindMetricsQueryHandler
        implements QueryHandler<FindMetricsQuery, MetricsResponse> {

    private final FindMetricsUC uc;

    @Override
    public MetricsResponse handle(final FindMetricsQuery query) {

        // Call the Use Case
        return uc.invoke();
    }
}

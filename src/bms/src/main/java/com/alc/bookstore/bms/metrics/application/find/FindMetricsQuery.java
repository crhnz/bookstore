package com.alc.bookstore.bms.metrics.application.find;

import com.alc.bookstore.bms.metrics.application.MetricsResponse;
import com.alc.bookstore.shared.domain.bus.query.Query;

public record FindMetricsQuery() implements Query<MetricsResponse> {}

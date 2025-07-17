package com.alc.bookstore.bms.metrics.domain;

public interface MetricRepository {

    void increaseBooksCounter();

    Metric search();
}

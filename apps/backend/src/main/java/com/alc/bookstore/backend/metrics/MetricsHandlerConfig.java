package com.alc.bookstore.backend.metrics;

import com.alc.bookstore.bms.metrics.application.find.FindMetricsQuery;
import com.alc.bookstore.bms.metrics.application.find.FindMetricsQueryHandler;
import com.alc.bookstore.bms.metrics.infrastructure.IncreaseBooksCounterSubscriber;
import com.alc.bookstore.shared.domain.book.Constants;
import com.alc.bookstore.shared.domain.bus.event.EventBus;
import com.alc.bookstore.shared.domain.bus.query.QueryBus;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@AllArgsConstructor
public class MetricsHandlerConfig {

    private final EventBus eventBus;
    private final QueryBus queryBus;
    private final FindMetricsQueryHandler findMetricsQueryHandler;
    private final IncreaseBooksCounterSubscriber increaseBooksCounterSubscriber;

    @EventListener(ApplicationReadyEvent.class)
    public void register() {

        // Register query handlers
        queryBus.registerHandler(FindMetricsQuery.class, findMetricsQueryHandler);

        // Subscribe to events
        eventBus.subscribe(Constants.createdBookTopic, increaseBooksCounterSubscriber);
    }
}

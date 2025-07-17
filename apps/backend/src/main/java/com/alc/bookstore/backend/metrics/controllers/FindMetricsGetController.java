package com.alc.bookstore.backend.metrics.controllers;

import com.alc.bookstore.backend.spring.APIController;
import com.alc.bookstore.bms.metrics.application.MetricsResponse;
import com.alc.bookstore.bms.metrics.application.find.FindMetricsQuery;
import com.alc.bookstore.shared.domain.DomainError;
import com.alc.bookstore.shared.domain.bus.command.CommandBus;
import com.alc.bookstore.shared.domain.bus.query.QueryBus;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public final class FindMetricsGetController extends APIController {

    public FindMetricsGetController(final QueryBus queryBus, final CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @Override
    public Map<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>();
    }

    @GetMapping
    public ResponseEntity<MetricsResponse> findAll() {
        FindMetricsQuery query = new FindMetricsQuery();

        MetricsResponse result = ask(query);
        return ResponseEntity.ok(result);
    }
}

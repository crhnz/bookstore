package com.alc.bookstore.shared.domain.service;

import com.alc.bookstore.shared.domain.logger.Logger;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class ExecutionTimerDS {
    private static final String START_TIME_KEY = "executionStartTime";
    private static final String IDENTIFIER_KEY = "identifier";

    private final Logger logger;

    public static Map<String, Object> initialize(final String identifier, final ZonedDateTime t) {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put(IDENTIFIER_KEY, identifier);
        ctx.put(START_TIME_KEY, t.toInstant().toEpochMilli());
        return ctx;
    }

    public void invoke(final Map<String, Object> ctx) {
        String identifier = (String) ctx.get(IDENTIFIER_KEY);
        Long start = (Long) ctx.get(START_TIME_KEY);
        long duration = System.currentTimeMillis() - start;
        logger.info("Cristian's Metrics => Completed: " + identifier + " in " + duration + " ms");
    }
}

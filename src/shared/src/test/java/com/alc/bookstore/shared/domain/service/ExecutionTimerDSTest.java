package com.alc.bookstore.shared.domain.service;

import static org.mockito.Mockito.*;

import com.alc.bookstore.shared.domain.logger.Logger;
import java.time.ZonedDateTime;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExecutionTimerDSTest {

    private Logger logger;
    private ExecutionTimerDS timer;

    @BeforeEach
    void setUp() {
        logger = mock(Logger.class);
        timer = new ExecutionTimerDS(logger);
    }

    @Test
    void invokeShouldLogCompletionWithDuration() throws InterruptedException {
        String identifier = "MyUC";
        ZonedDateTime now = ZonedDateTime.now();
        Map<String, Object> ctx = ExecutionTimerDS.initialize(identifier, now);

        // Sleep to ensure measurable duration
        Thread.sleep(20); // NOSONAR

        timer.invoke(ctx);

        // Verify that logger.info was called with the expected prefix
        verify(logger).info(startsWith("Cristian's Metrics => Completed: MyUC in "));
    }
}

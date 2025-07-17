package com.alc.bookstore.shared.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.alc.bookstore.shared.domain.logger.Logger;
import java.util.Optional;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RetryPolicyDSTest {

    private Logger logger;
    private RetryPolicyDS retryPolicy;

    @BeforeEach
    void setUp() {
        logger = mock(Logger.class);
        retryPolicy = new RetryPolicyDS(3, 10, logger); // 10ms delay para test rápido
    }

    @Test
    void shouldReturnResultWhenOperationSucceedsFirstTry() {
        Optional<String> result = retryPolicy.execute(() -> "Hello");

        assertTrue(result.isPresent());
        assertEquals("Hello", result.get());

        verify(logger).info("RetryPolicyDS: attempt 1/3");
        verifyNoMoreInteractions(logger);
    }

    @Test
    void shouldRetryAndSucceedOnSecondAttempt() {
        Supplier<String> flakySupplier =
                new Supplier<>() {
                    int count = 0;

                    @Override
                    public String get() {
                        if (count++ == 0) {
                            throw new RuntimeException("Fail first");
                        }
                        return "Recovered";
                    }
                };

        Optional<String> result = retryPolicy.execute(flakySupplier);

        assertTrue(result.isPresent());
        assertEquals("Recovered", result.get());

        verify(logger).info("RetryPolicyDS: attempt 1/3");
        verify(logger).warning(contains("RetryPolicyDS: attempt 1/3"));
        verify(logger).info("RetryPolicyDS: attempt 2/3");
        verifyNoMoreInteractions(logger);
    }

    @Test
    void shouldReturnEmptyWhenAllAttemptsFail() {
        Supplier<String> alwaysFails =
                () -> {
                    throw new RuntimeException("Always fails");
                };

        Optional<String> result = retryPolicy.execute(alwaysFails);

        assertTrue(result.isEmpty());

        verify(logger).info("RetryPolicyDS: attempt 1/3");
        verify(logger).warning(contains("RetryPolicyDS: attempt 1/3"));
        verify(logger).info("RetryPolicyDS: attempt 2/3");
        verify(logger).warning(contains("RetryPolicyDS: attempt 2/3"));
        verify(logger).info("RetryPolicyDS: attempt 3/3");
        verify(logger).warning(contains("RetryPolicyDS: attempt 3/3"));
        verify(logger).error("RetryPolicyDS: All attempts failed");
    }

    @Test
    void shouldHandleInterruptedExceptionGracefully() throws InterruptedException {
        // Use a policy with a long delay to force interrupt
        RetryPolicyDS interruptPolicy = new RetryPolicyDS(3, 10000, logger);

        Thread testThread =
                new Thread(
                        () -> {
                            Optional<String> result =
                                    interruptPolicy.execute(
                                            () -> {
                                                throw new RuntimeException("Fail");
                                            });
                            assertTrue(result.isEmpty());
                        });

        testThread.start();
        Thread.sleep(100); // let it start
        testThread.interrupt();
        testThread.join();
    }
}

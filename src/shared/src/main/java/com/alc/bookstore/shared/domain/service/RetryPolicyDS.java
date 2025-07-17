package com.alc.bookstore.shared.domain.service;

import com.alc.bookstore.shared.domain.logger.Logger;
import java.util.Optional;
import java.util.function.Supplier;

public class RetryPolicyDS {
    private final Logger logger;

    private final int maxAttempts;
    private final long delayMillis;

    public RetryPolicyDS(final int maxAttempts, final long delayMillis, final Logger logger) {
        this.maxAttempts = maxAttempts;
        this.delayMillis = delayMillis;
        this.logger = logger;
    }

    public <T> Optional<T> execute(final Supplier<T> operation) {
        int attempt = 0;

        while (attempt < maxAttempts) {
            int currentAttempt = attempt + 1;
            try {
                logger.info("RetryPolicyDS: attempt " + currentAttempt + "/" + (maxAttempts));
                return Optional.ofNullable(operation.get());
            } catch (Exception ex) {
                logger.warning(
                        "RetryPolicyDS: attempt "
                                + currentAttempt
                                + "/"
                                + (maxAttempts)
                                + " failed: "
                                + ex);

                attempt++;
                if (attempt < maxAttempts) {
                    try {
                        Thread.sleep(delayMillis);
                    } catch (InterruptedException ignored) {
                        Thread.currentThread().interrupt();
                        return Optional.empty();
                    }
                }
            }
        }

        logger.error("RetryPolicyDS: All attempts failed");
        return Optional.empty();
    }
}

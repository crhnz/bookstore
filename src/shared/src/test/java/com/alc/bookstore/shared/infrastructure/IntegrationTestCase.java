package com.alc.bookstore.shared.infrastructure;

public abstract class IntegrationTestCase {
    private final int MAX_ATTEMPTS = 3;
    private final int MILLIS_TO_WAIT_BETWEEN_RETRIES = 300;

    protected void eventually(final Runnable assertion) throws Exception {
        int attempts = 0;
        boolean allOk = false;

        while (!allOk) {
            try {
                assertion.run();

                allOk = true;
            } catch (Throwable error) {
                attempts++;

                if (attempts > MAX_ATTEMPTS) {
                    throw new Exception(
                            String.format(
                                    "Could not assert after some retries. Last error: %s",
                                    error.getMessage()));
                }

                Thread.sleep(MILLIS_TO_WAIT_BETWEEN_RETRIES);
            }
        }
    }
}

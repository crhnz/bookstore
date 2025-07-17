package com.alc.bookstore.springshared.infrastructure.spring.interceptors;

import com.alc.bookstore.shared.domain.logger.Logger;
import com.alc.bookstore.shared.domain.service.RetryPolicyDS;
import com.alc.bookstore.shared.infrastructure.logger.SLF4JLogger;
import java.io.IOException;
import java.util.Optional;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public final class RetryInterceptor implements ClientHttpRequestInterceptor {

    private final Logger logger = new SLF4JLogger(RetryInterceptor.class);
    private final RetryPolicyDS retryPolicyDS = new RetryPolicyDS(3, 5000, logger);

    @Override
    public ClientHttpResponse intercept(
            final @NonNull HttpRequest request,
            final @NonNull byte[] body,
            final @NonNull ClientHttpRequestExecution execution)
            throws IOException {
        Optional<ClientHttpResponse> response =
                retryPolicyDS.execute(
                        () -> {
                            try {
                                return execution.execute(request, body);
                            } catch (IOException e) {
                                throw new RuntimeException(e); // NOSONAR
                            }
                        });

        if (response.isPresent()) {
            return response.get();
        } else {
            throw new IOException("All retry attempts failed");
        }
    }
}

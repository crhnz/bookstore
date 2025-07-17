package com.alc.bookstore.backend.fake.service;

import com.alc.bookstore.shared.domain.annotation.UseCaseComponent;
import org.springframework.web.client.RestTemplate;

@UseCaseComponent
public final class RetryDemoService {

    private final RestTemplate restTemplate;

    public RetryDemoService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callUnstableApi() {
        return restTemplate.getForObject("http://localhost:9999/fake", String.class);
    }
}

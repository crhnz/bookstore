package com.alc.bookstore.springshared.infrastructure.spring.interceptors.config;

import com.alc.bookstore.springshared.infrastructure.spring.interceptors.RetryInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TemplateInterceptorsConfig {

    @Bean
    public RestTemplate restTemplate(final RetryInterceptor retryInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(retryInterceptor);
        return restTemplate;
    }

    /*
     * @Bean
     * public WebClient webClient(RetryPolicyDS retryPolicy) {
     * return WebClient.builder()
     * .filter((request, next) -> {
     * return retryPolicy.execute(() ->
     * next.exchange(request).block()).orElseThrow(
     * () -> new RuntimeException("All attempts failed")
     * );
     * })
     * .build();
     * }
     *
     * RetryPolicyDS retryPolicy = new RetryPolicyDS(3, 1000, logger);
     * OkHttpClient client = new OkHttpClient.Builder()
     * .addInterceptor(new OkHttpRetryInterceptor(retryPolicy))
     * .build();
     *
     *
     * RetryPolicyDS retryPolicy = new RetryPolicyDS(3, 1000, logger);
     * HttpGet request = new HttpGet("http://localhost:8080/api/test");
     * Optional<CloseableHttpResponse> response = retryPolicy.execute(() -> {
     * try {
     * return httpClient.execute(request);
     * } catch (IOException e) {
     * throw new RuntimeException(e);
     * }
     * });
     *
     */
}

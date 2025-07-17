package com.alc.bookstore.springshared.infrastructure.spring.interceptors.config;

import com.alc.bookstore.springshared.infrastructure.spring.interceptors.EndpointMetricsInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class HandlerInterceptorsConfig implements WebMvcConfigurer {

    private final EndpointMetricsInterceptor endpointMetricsInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(endpointMetricsInterceptor);
    }
}

package com.alc.bookstore.springshared.infrastructure.spring.interceptors;

import com.alc.bookstore.shared.domain.logger.Logger;
import com.alc.bookstore.shared.domain.service.ExecutionTimerDS;
import com.alc.bookstore.shared.infrastructure.logger.SLF4JLogger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class EndpointMetricsInterceptor implements HandlerInterceptor {

    private static final String CTX_KEY = "ctx";

    private final Logger logger = new SLF4JLogger(EndpointMetricsInterceptor.class);
    private final ExecutionTimerDS executionTimerDS = new ExecutionTimerDS(logger);

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final @NonNull HttpServletResponse response,
            final @NonNull Object handler) {
        Map<String, Object> ctx =
                ExecutionTimerDS.initialize(
                        request.getMethod() + " " + request.getRequestURI(), ZonedDateTime.now());
        request.setAttribute(CTX_KEY, ctx);
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void afterCompletion(
            final HttpServletRequest request,
            final @NonNull HttpServletResponse response,
            final @NonNull Object handler,
            final Exception ex) {
        Map<String, Object> ctx = (Map<String, Object>) request.getAttribute(CTX_KEY);
        executionTimerDS.invoke(ctx);
    }
}

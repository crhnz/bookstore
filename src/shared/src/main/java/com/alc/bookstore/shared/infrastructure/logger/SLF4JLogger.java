package com.alc.bookstore.shared.infrastructure.logger;

import com.alc.bookstore.shared.domain.logger.Logger;
import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

public class SLF4JLogger implements Logger {

    private final org.slf4j.Logger logger;

    public SLF4JLogger(final Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    private String enrichMessage(final String message, final Map<String, Serializable> context) {
        if (context == null || context.isEmpty()) {
            return message;
        }
        String ctx =
                context.entrySet().stream()
                        .map(e -> e.getKey() + "=" + e.getValue())
                        .collect(Collectors.joining(", ", " {", "}"));
        return message + ctx;
    }

    @Override
    public void debug(final String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    @Override
    public void debug(final String message, final Map<String, Serializable> context) {
        if (logger.isDebugEnabled()) {
            logger.debug(enrichMessage(message, context));
        }
    }

    @Override
    public void info(final String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    @Override
    public void info(final String message, final Map<String, Serializable> context) {
        if (logger.isInfoEnabled()) {
            logger.info(enrichMessage(message, context));
        }
    }

    @Override
    public void warning(final String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    @Override
    public void warning(final String message, final Map<String, Serializable> context) {
        if (logger.isWarnEnabled()) {
            logger.warn(enrichMessage(message, context));
        }
    }

    @Override
    public void error(final String message) {
        if (logger.isErrorEnabled()) {
            logger.error(message);
        }
    }

    @Override
    public void error(final String message, final Map<String, Serializable> context) {
        if (logger.isErrorEnabled()) {
            logger.error(enrichMessage(message, context));
        }
    }
}

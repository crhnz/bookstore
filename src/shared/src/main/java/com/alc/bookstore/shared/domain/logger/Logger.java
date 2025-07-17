package com.alc.bookstore.shared.domain.logger;

import java.io.Serializable;
import java.util.Map;

public interface Logger {
    void debug(String message);

    void debug(String message, Map<String, Serializable> context);

    void info(String message);

    void info(String message, Map<String, Serializable> context);

    void warning(String message);

    void warning(String message, Map<String, Serializable> context);

    void error(String message);

    void error(String message, Map<String, Serializable> context);
}

package com.alc.bookstore.shared.infrastructure.logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

public class SLF4JLoggerShouldTest {

    @Test
    void it_should_log_debug_if_debug_is_enabled_message() {
        Logger slf4jLogger = (Logger) LoggerFactory.getLogger(SLF4JLoggerShouldTest.class);
        slf4jLogger.setLevel(Level.DEBUG);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        slf4jLogger.addAppender(listAppender);

        SLF4JLogger logger = new SLF4JLogger(SLF4JLoggerShouldTest.class);
        logger.debug("Hello test", null);
        logger.debug("Hello there!");

        List<ILoggingEvent> logs = listAppender.list;

        assertFalse(logs.isEmpty());
        assertTrue(logs.get(0).getFormattedMessage().contains("Hello test"));
        assertTrue(logs.get(1).getFormattedMessage().contains("Hello there"));
    }

    @Test
    void it_should_not_log_debug_if_debug_is_not_enabled_message() {
        Logger slf4jLogger = (Logger) LoggerFactory.getLogger(SLF4JLoggerShouldTest.class);
        slf4jLogger.setLevel(Level.ERROR);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        slf4jLogger.addAppender(listAppender);

        SLF4JLogger logger = new SLF4JLogger(SLF4JLoggerShouldTest.class);
        logger.debug("Hello test", Map.of("foo", "bar"));
        logger.debug("Hello there!");

        List<ILoggingEvent> logs = listAppender.list;

        assertTrue(logs.isEmpty());
    }

    @Test
    void it_should_log_info_if_info_is_enabled_message() {
        Logger slf4jLogger = (Logger) LoggerFactory.getLogger(SLF4JLoggerShouldTest.class);
        slf4jLogger.setLevel(Level.INFO);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        slf4jLogger.addAppender(listAppender);

        SLF4JLogger logger = new SLF4JLogger(SLF4JLoggerShouldTest.class);
        logger.info("Hello test", Map.of());
        logger.info("Hello there!");

        List<ILoggingEvent> logs = listAppender.list;

        assertFalse(logs.isEmpty());
        assertTrue(logs.get(0).getFormattedMessage().contains("Hello test"));
        assertTrue(logs.get(1).getFormattedMessage().contains("Hello there"));
    }

    @Test
    void it_should_not_log_info_if_info_is_not_enabled_message() {
        Logger slf4jLogger = (Logger) LoggerFactory.getLogger(SLF4JLoggerShouldTest.class);
        slf4jLogger.setLevel(Level.ERROR);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        slf4jLogger.addAppender(listAppender);

        SLF4JLogger logger = new SLF4JLogger(SLF4JLoggerShouldTest.class);
        logger.info("Hello test", Map.of("foo", "bar"));
        logger.info("Hello there!");

        List<ILoggingEvent> logs = listAppender.list;

        assertTrue(logs.isEmpty());
    }

    @Test
    void it_should_log_warn_if_warn_is_enabled_message() {
        Logger slf4jLogger = (Logger) LoggerFactory.getLogger(SLF4JLoggerShouldTest.class);
        slf4jLogger.setLevel(Level.WARN);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        slf4jLogger.addAppender(listAppender);

        SLF4JLogger logger = new SLF4JLogger(SLF4JLoggerShouldTest.class);
        logger.warning("Hello test", Map.of("foo", "bar"));
        logger.warning("Hello there!");

        List<ILoggingEvent> logs = listAppender.list;

        assertFalse(logs.isEmpty());
        assertTrue(logs.get(0).getFormattedMessage().contains("Hello test"));
        assertTrue(logs.get(0).getFormattedMessage().contains("foo=bar"));
        assertTrue(logs.get(1).getFormattedMessage().contains("Hello there"));
    }

    @Test
    void it_should_not_log_warn_if_warn_is_not_enabled_message() {
        Logger slf4jLogger = (Logger) LoggerFactory.getLogger(SLF4JLoggerShouldTest.class);
        slf4jLogger.setLevel(Level.ERROR);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        slf4jLogger.addAppender(listAppender);

        SLF4JLogger logger = new SLF4JLogger(SLF4JLoggerShouldTest.class);
        logger.warning("Hello test", Map.of("foo", "bar"));
        logger.warning("Hello there!");

        List<ILoggingEvent> logs = listAppender.list;

        assertTrue(logs.isEmpty());
    }

    @Test
    void it_should_log_error_if_error_is_enabled_message() {
        Logger slf4jLogger = (Logger) LoggerFactory.getLogger(SLF4JLoggerShouldTest.class);
        slf4jLogger.setLevel(Level.ERROR);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        slf4jLogger.addAppender(listAppender);

        SLF4JLogger logger = new SLF4JLogger(SLF4JLoggerShouldTest.class);
        logger.error("Hello test", Map.of("foo", "bar"));
        logger.error("Hello there!");

        List<ILoggingEvent> logs = listAppender.list;

        assertFalse(logs.isEmpty());
        assertTrue(logs.get(0).getFormattedMessage().contains("Hello test"));
        assertTrue(logs.get(0).getFormattedMessage().contains("foo=bar"));
        assertTrue(logs.get(1).getFormattedMessage().contains("Hello there!"));
    }

    @Test
    void it_should_not_log_error_if_error_is_not_enabled_message() {
        Logger slf4jLogger = (Logger) LoggerFactory.getLogger(SLF4JLoggerShouldTest.class);
        slf4jLogger.setLevel(Level.OFF);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        slf4jLogger.addAppender(listAppender);

        SLF4JLogger logger = new SLF4JLogger(SLF4JLoggerShouldTest.class);
        logger.error("Hello test", Map.of("foo", "bar"));
        logger.error("Hello there!");

        List<ILoggingEvent> logs = listAppender.list;

        assertTrue(logs.isEmpty());
    }
}

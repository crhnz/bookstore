package com.alc.bookstore.shared.infrastructure.bus.command;

import static org.junit.jupiter.api.Assertions.*;

import com.alc.bookstore.shared.domain.bus.command.Command;
import com.alc.bookstore.shared.domain.bus.command.CommandHandlerExecutionError;
import com.alc.bookstore.shared.domain.bus.command.CommandNotRegisteredError;
import com.alc.bookstore.shared.infrastructure.IntegrationTestCase;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

final class InMemoryCommandBusShouldTest extends IntegrationTestCase {

    private final InMemoryCommandBus commandBus = new InMemoryCommandBus();

    @Test
    void dispatch_a_command_with_a_registered_handler() throws Exception {
        DummyCommand command = new DummyCommand();
        AtomicBoolean handled = new AtomicBoolean(false);

        commandBus.registerHandler(DummyCommand.class, c -> handled.set(true));

        commandBus.dispatch(command);

        eventually(() -> assertTrue(handled.get()));
    }

    @Test
    void throw_an_exception_if_command_has_no_handler() {
        DummyCommand command = new DummyCommand();

        CommandHandlerExecutionError error =
                assertThrows(
                        CommandHandlerExecutionError.class, () -> commandBus.dispatch(command));

        assertInstanceOf(CommandNotRegisteredError.class, error.getCause());
        assertEquals(
                DummyCommand.class,
                ((CommandNotRegisteredError) error.getCause()).getCommandClass());
    }

    @Test
    void wrap_exceptions_thrown_by_the_handler() {
        DummyCommand command = new DummyCommand();

        commandBus.registerHandler(
                DummyCommand.class,
                c -> {
                    throw new IllegalStateException("Handler failure");
                });

        CommandHandlerExecutionError error =
                assertThrows(
                        CommandHandlerExecutionError.class, () -> commandBus.dispatch(command));

        assertInstanceOf(IllegalStateException.class, error.getCause());
        assertEquals("Handler failure", error.getCause().getMessage());
    }

    // Dummy command class for testing
    private static final class DummyCommand implements Command {}
}

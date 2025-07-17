package com.alc.bookstore.shared.infrastructure.bus.command;

import com.alc.bookstore.shared.domain.annotation.CommandBusComponent;
import com.alc.bookstore.shared.domain.bus.command.Command;
import com.alc.bookstore.shared.domain.bus.command.CommandBus;
import com.alc.bookstore.shared.domain.bus.command.CommandHandler;
import com.alc.bookstore.shared.domain.bus.command.CommandHandlerExecutionError;
import com.alc.bookstore.shared.domain.bus.command.CommandNotRegisteredError;
import java.util.HashMap;
import java.util.Map;

@CommandBusComponent
public final class InMemoryCommandBus implements CommandBus {

    private final Map<Class<? extends Command>, CommandHandler<? extends Command>> handlers =
            new HashMap<>();

    @Override
    public <C extends Command> void registerHandler(
            final Class<C> commandClass, final CommandHandler<C> handler) {
        handlers.put(commandClass, handler);
    }

    @Override
    public void dispatch(final Command command) throws CommandHandlerExecutionError {
        CommandHandler<Command> handler = getHandler(command);

        if (handler == null) {
            throw new CommandHandlerExecutionError(
                    new CommandNotRegisteredError(command.getClass()));
        }

        try {
            handler.handle(command);
        } catch (Exception e) {
            throw new CommandHandlerExecutionError(e);
        }
    }

    @SuppressWarnings("unchecked")
    private CommandHandler<Command> getHandler(final Command command) {
        return (CommandHandler<Command>) handlers.get(command.getClass());
    }
}

package com.alc.bookstore.shared.domain.bus.command;

public interface CommandBus {

    <C extends Command> void registerHandler(Class<C> commandClass, CommandHandler<C> handler);

    void dispatch(Command command) throws CommandHandlerExecutionError;
}

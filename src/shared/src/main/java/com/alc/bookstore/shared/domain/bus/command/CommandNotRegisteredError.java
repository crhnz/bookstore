package com.alc.bookstore.shared.domain.bus.command;

public final class CommandNotRegisteredError extends Exception {

    private final Class<? extends Command> command;

    public CommandNotRegisteredError(final Class<? extends Command> command) {
        super(
                String.format(
                        "The command <%s> hasn't a command handler associated",
                        command.toString()));
        this.command = command;
    }

    public Class<? extends Command> getCommandClass() {
        return this.command;
    }
}

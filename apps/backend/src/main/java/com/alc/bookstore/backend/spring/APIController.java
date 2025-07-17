package com.alc.bookstore.backend.spring;

import com.alc.bookstore.shared.domain.DomainError;
import com.alc.bookstore.shared.domain.bus.command.Command;
import com.alc.bookstore.shared.domain.bus.command.CommandBus;
import com.alc.bookstore.shared.domain.bus.command.CommandHandlerExecutionError;
import com.alc.bookstore.shared.domain.bus.query.Query;
import com.alc.bookstore.shared.domain.bus.query.QueryBus;
import com.alc.bookstore.shared.domain.bus.query.QueryHandlerExecutionError;
import com.alc.bookstore.shared.domain.bus.query.Response;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public abstract class APIController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    protected void dispatch(final Command command) throws CommandHandlerExecutionError {
        commandBus.dispatch(command);
    }

    protected <R extends Response> R ask(final Query<R> query) throws QueryHandlerExecutionError {
        return queryBus.ask(query);
    }

    public abstract Map<Class<? extends DomainError>, HttpStatus> errorMapping();
}

package com.alc.bookstore.shared.infrastructure.bus.query;

import com.alc.bookstore.shared.domain.annotation.QueryBusComponent;
import com.alc.bookstore.shared.domain.bus.query.Query;
import com.alc.bookstore.shared.domain.bus.query.QueryBus;
import com.alc.bookstore.shared.domain.bus.query.QueryHandler;
import com.alc.bookstore.shared.domain.bus.query.QueryHandlerExecutionError;
import com.alc.bookstore.shared.domain.bus.query.QueryNotRegisteredError;
import com.alc.bookstore.shared.domain.bus.query.Response;
import java.util.HashMap;
import java.util.Map;

@QueryBusComponent
public class InMemoryQueryBus implements QueryBus {
    private final Map<Class<? extends Query<?>>, QueryHandler<?, ?>> handlers = new HashMap<>();

    @Override
    public <Q extends Query<R>, R extends Response> void registerHandler(
            final Class<Q> queryClass, final QueryHandler<Q, R> handler) {
        handlers.put(queryClass, handler);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R extends Response> R ask(final Query<R> query) throws QueryHandlerExecutionError {
        QueryHandler<Query<R>, R> handler = getHandler(query);

        if (handler == null) {
            throw new QueryHandlerExecutionError(
                    new QueryNotRegisteredError(
                            (Class<? extends Query<? extends Response>>) query.getClass()));
        }

        return handler.handle(query);
    }

    @SuppressWarnings("unchecked")
    private <R extends Response> QueryHandler<Query<R>, R> getHandler(final Query<R> query) {
        return (QueryHandler<Query<R>, R>) handlers.get(query.getClass());
    }
}

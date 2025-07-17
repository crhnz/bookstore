package com.alc.bookstore.shared.domain.bus.query;

public interface QueryBus {

    <R extends Response> R ask(Query<R> query) throws QueryHandlerExecutionError;

    <Q extends Query<R>, R extends Response> void registerHandler(
            Class<Q> queryClass, QueryHandler<Q, R> handler);
}

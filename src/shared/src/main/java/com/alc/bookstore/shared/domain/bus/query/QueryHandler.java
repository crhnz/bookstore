package com.alc.bookstore.shared.domain.bus.query;

@FunctionalInterface
public interface QueryHandler<Q extends Query<R>, R extends Response> {
    R handle(Q query);
}

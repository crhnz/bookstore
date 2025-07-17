package com.alc.bookstore.shared.domain.bus.query;

public final class QueryNotRegisteredError extends Exception {

    private final Class<? extends Query<? extends Response>> query;

    public QueryNotRegisteredError(final Class<? extends Query<? extends Response>> query) {
        super(String.format("The query <%s> hasn't a query handler associated", query.toString()));
        this.query = query;
    }

    public Class<? extends Query<? extends Response>> getQueryClass() {
        return this.query;
    }
}

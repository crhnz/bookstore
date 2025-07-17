package com.alc.bookstore.shared.infrastructure.bus.query;

import static org.junit.jupiter.api.Assertions.*;

import com.alc.bookstore.shared.domain.bus.query.Query;
import com.alc.bookstore.shared.domain.bus.query.QueryHandlerExecutionError;
import com.alc.bookstore.shared.domain.bus.query.QueryNotRegisteredError;
import com.alc.bookstore.shared.domain.bus.query.Response;
import com.alc.bookstore.shared.infrastructure.IntegrationTestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

final class InMemoryQueryBusShouldTest extends IntegrationTestCase {

    private final InMemoryQueryBus queryBus = new InMemoryQueryBus();

    @Test
    void return_a_response_from_a_registered_handler() throws Exception {
        DummyQuery query = new DummyQuery();
        DummyResponse expected = new DummyResponse("Hello Query");

        queryBus.registerHandler(DummyQuery.class, q -> expected);

        DummyResponse result = queryBus.ask(query);

        assertEquals(expected, result);
    }

    @Test
    void throw_an_exception_if_query_handler_not_registered() {
        DummyQuery query = new DummyQuery();

        QueryHandlerExecutionError error =
                assertThrows(QueryHandlerExecutionError.class, () -> queryBus.ask(query));

        assertEquals(
                DummyQuery.class, ((QueryNotRegisteredError) error.getCause()).getQueryClass());
    }

    // Dummy Query + Response
    private static final class DummyQuery implements Query<DummyResponse> {}

    @Data
    @AllArgsConstructor
    private static final class DummyResponse implements Response {
        private final String value;
    }
}

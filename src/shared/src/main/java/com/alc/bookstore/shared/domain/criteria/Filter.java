package com.alc.bookstore.shared.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Filter {
    private String field;
    private FilterOperator operator;
    private String value;

    public static Filter create(
            final String field, final FilterOperator operator, final String value) {
        return new Filter(field, operator, value);
    }
}

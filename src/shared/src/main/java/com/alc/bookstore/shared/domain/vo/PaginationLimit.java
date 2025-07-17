package com.alc.bookstore.shared.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class PaginationLimit extends IntValueObject {
    private static final int MAX_LIMIT = 100;

    private PaginationLimit(final int value) {
        super(value);

        ensureValue(value);
    }

    private void ensureValue(final int value) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        if (value > MAX_LIMIT) {
            throw new IllegalArgumentException("Limit must not exceed " + MAX_LIMIT);
        }
    }

    public static PaginationLimit of(final Integer limit) {
        return limit != null ? new PaginationLimit(limit) : new PaginationLimit(10);
    }
}

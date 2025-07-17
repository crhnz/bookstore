package com.alc.bookstore.shared.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class PaginationOffset extends IntValueObject {

    private PaginationOffset(final int value) {
        super(value);

        ensureValue(value);
    }

    private void ensureValue(final int value) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException("Offset must be 0 or greater");
        }
    }

    public static PaginationOffset of(final Integer offset) {
        return offset != null ? new PaginationOffset(offset) : new PaginationOffset(0);
    }
}

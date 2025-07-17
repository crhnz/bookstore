package com.alc.bookstore.shared.domain.vo;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class OrderByValueObject extends StringValueObject {

    private OrderByValueObject(final String column) {
        super(column);

        ensureValue(column);
    }

    private void ensureValue(final String value) throws IllegalArgumentException {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("OrderBy must not be blank");
        }
    }

    public static OrderByValueObject of(final String value) {
        return new OrderByValueObject(value);
    }

    public static Optional<OrderByValueObject> ofNullable(final String value) {
        return Optional.ofNullable(value).map(OrderByValueObject::new);
    }
}

package com.alc.bookstore.shared.domain.vo;

import com.alc.bookstore.shared.domain.criteria.OrderType;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class OrderTypeValueObject extends StringValueObject {

    private OrderTypeValueObject(final String column) {
        super(column);

        ensureValue(column);
    }

    private void ensureValue(final String value) throws IllegalArgumentException {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("OrderBy must not be blank");
        }

        OrderType.valueOf(value);
    }

    public static OrderTypeValueObject of(final String value) {
        return new OrderTypeValueObject(value);
    }

    public static Optional<OrderTypeValueObject> ofNullable(final String value) {
        return Optional.ofNullable(value).map(OrderTypeValueObject::new);
    }
}

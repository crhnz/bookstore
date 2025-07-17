package com.alc.bookstore.shared.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Order {
    private final String orderBy;
    private final OrderType orderType;

    public static Order fromValues(final String orderBy, final String orderType) {
        if (orderBy == null) {
            return Order.none();
        }

        OrderType type =
                OrderType.valueOf(
                        orderType != null ? orderType.toLowerCase() : OrderType.ASC.value());

        return new Order(orderBy, type);
    }

    public static Order none() {
        return new Order("", OrderType.NONE);
    }

    public static Order desc(final String orderBy) {
        return new Order(orderBy, OrderType.DESC);
    }

    public static Order asc(final String orderBy) {
        return new Order(orderBy, OrderType.ASC);
    }
}

package com.alc.bookstore.shared.domain.criteria;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public final class Criteria {

    private final List<Filter> filters;
    private final Order order;
    private final Integer limit;
    private final Integer offset;

    Criteria(
            final List<Filter> filters,
            final Order order,
            final Integer limit,
            final Integer offset) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.offset = offset;
    }

    public static CriteriaBuilder builder(final int offset, final int limit) {
        return new CriteriaBuilder(limit, offset);
    }

    public static class CriteriaBuilder {
        private final List<Filter> filters;
        private final int limit;
        private final int offset;
        private Order order;

        CriteriaBuilder(final int limit, final int offset) {
            this.filters = new ArrayList<>();
            this.limit = limit;
            this.offset = offset;
            this.order = Order.none();
        }

        public CriteriaBuilder filtersIfNotNull(
                final String field, final FilterOperator operator, final String value) {
            if (value != null) {
                this.filters.add(Filter.create(field, operator, value));
            }
            return this;
        }

        public CriteriaBuilder orderIfNotNull(final Order order) {
            if (order != null) {
                this.order = order;
            }
            return this;
        }

        public Criteria build() {
            return new Criteria(this.filters, this.order, this.limit, this.offset);
        }
    }
}

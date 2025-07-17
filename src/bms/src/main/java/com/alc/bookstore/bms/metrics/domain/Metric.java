package com.alc.bookstore.bms.metrics.domain;

import com.alc.bookstore.bms.metrics.domain.vo.BooksCounter;
import com.alc.bookstore.shared.domain.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Metric extends AggregateRoot {

    @Data
    @AllArgsConstructor
    public static class Books {

        private BooksCounter counter;

        public static Books create(final BooksCounter counter) {
            return new Books(counter);
        }
    }

    private Books books;

    public static Metric create(final BooksCounter counter) {
        return new Metric(Books.create(counter));
    }
}

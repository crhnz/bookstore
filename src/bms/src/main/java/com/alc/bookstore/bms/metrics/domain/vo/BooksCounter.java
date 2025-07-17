package com.alc.bookstore.bms.metrics.domain.vo;

import com.alc.bookstore.shared.domain.vo.IntValueObject;

public final class BooksCounter extends IntValueObject {
    public BooksCounter(final int value) {
        super(value);
    }

    public static BooksCounter of(final int value) {
        return new BooksCounter(value);
    }
}

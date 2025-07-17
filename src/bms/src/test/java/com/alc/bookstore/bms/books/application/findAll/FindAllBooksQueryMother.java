package com.alc.bookstore.bms.books.application.findAll;

import com.alc.bookstore.bms.books.application.findall.FindAllBooksQuery;

public class FindAllBooksQueryMother {

    public static FindAllBooksQuery create(
            String title,
            String genre,
            String orderBy,
            String orderType,
            Integer offset,
            Integer limit) {
        return new FindAllBooksQuery(title, genre, orderBy, orderType, offset, limit);
    }

    public static FindAllBooksQuery empty(int offset, int limit) {
        return create(null, null, null, null, offset, limit);
    }
}

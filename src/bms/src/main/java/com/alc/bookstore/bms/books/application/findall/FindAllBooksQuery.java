package com.alc.bookstore.bms.books.application.findall;

import com.alc.bookstore.bms.books.application.BookResponse;
import com.alc.bookstore.shared.application.PaginatedResponse;
import com.alc.bookstore.shared.domain.bus.query.Query;

public record FindAllBooksQuery(
        String title, String genre, String orderBy, String orderType, Integer offset, Integer limit)
        implements Query<PaginatedResponse<BookResponse>> {}

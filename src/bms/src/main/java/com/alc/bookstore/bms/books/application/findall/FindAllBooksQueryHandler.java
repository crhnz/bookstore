package com.alc.bookstore.bms.books.application.findall;

import com.alc.bookstore.bms.books.application.BookResponse;
import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.bms.books.domain.vo.BookTitle;
import com.alc.bookstore.shared.application.PaginatedResponse;
import com.alc.bookstore.shared.domain.annotation.QueryHandlerComponent;
import com.alc.bookstore.shared.domain.bus.query.QueryHandler;
import com.alc.bookstore.shared.domain.vo.OrderByValueObject;
import com.alc.bookstore.shared.domain.vo.OrderTypeValueObject;
import com.alc.bookstore.shared.domain.vo.PaginationLimit;
import com.alc.bookstore.shared.domain.vo.PaginationOffset;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@QueryHandlerComponent
public final class FindAllBooksQueryHandler
        implements QueryHandler<FindAllBooksQuery, PaginatedResponse<BookResponse>> {

    private final FindAllBooksUC uc;

    @Override
    public PaginatedResponse<BookResponse> handle(final FindAllBooksQuery query) {
        // Prepare and validate the input parameters
        Optional<BookTitle> title = BookTitle.ofNullable(query.title());
        Optional<BookGenre> genre = BookGenre.ofNullable(query.genre());
        Optional<OrderByValueObject> orderBy = OrderByValueObject.ofNullable(query.orderBy());
        Optional<OrderTypeValueObject> orderType =
                OrderTypeValueObject.ofNullable(query.orderType());
        PaginationLimit limit = PaginationLimit.of(query.limit());
        PaginationOffset offset = PaginationOffset.of(query.offset());

        // Call the Use Case
        return uc.invoke(
                title.orElse(null),
                genre.orElse(null),
                orderBy.orElse(null),
                orderType.orElse(null),
                limit,
                offset);
    }
}

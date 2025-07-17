package com.alc.bookstore.bms.books.application.findall;

import com.alc.bookstore.bms.books.application.BookResponse;
import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.bms.books.domain.BookRepository;
import com.alc.bookstore.bms.books.domain.vo.BookGenre;
import com.alc.bookstore.bms.books.domain.vo.BookTitle;
import com.alc.bookstore.shared.application.PaginatedResponse;
import com.alc.bookstore.shared.domain.annotation.UseCaseComponent;
import com.alc.bookstore.shared.domain.criteria.Criteria;
import com.alc.bookstore.shared.domain.criteria.FilterOperator;
import com.alc.bookstore.shared.domain.criteria.Order;
import com.alc.bookstore.shared.domain.vo.OrderByValueObject;
import com.alc.bookstore.shared.domain.vo.OrderTypeValueObject;
import com.alc.bookstore.shared.domain.vo.PaginationLimit;
import com.alc.bookstore.shared.domain.vo.PaginationOffset;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCaseComponent
public class FindAllBooksUC {

    private final BookRepository repository;

    public PaginatedResponse<BookResponse> invoke(
            final BookTitle title,
            final BookGenre genre,
            final OrderByValueObject orderBy,
            final OrderTypeValueObject orderType,
            final PaginationLimit limit,
            final PaginationOffset offset) {
        // Create the criteria
        Criteria criteria =
                Criteria.builder(offset.getValue(), limit.getValue())
                        .filtersIfNotNull(
                                "title",
                                FilterOperator.EQUAL,
                                title != null ? title.getValue() : null)
                        .filtersIfNotNull(
                                "genre",
                                FilterOperator.EQUAL,
                                genre != null ? genre.getValue() : null)
                        .orderIfNotNull(
                                Order.fromValues(
                                        orderBy != null ? orderBy.getValue() : null,
                                        orderType != null ? orderType.getValue() : null))
                        .build();

        // Look for the repository
        List<Book> books = repository.matching(criteria);

        // Transform books into the response
        List<BookResponse> responseList = books.stream().map(BookResponse::fromAggregate).toList();

        // Return the response
        return new PaginatedResponse<>(
                responseList, books.size(), offset.getValue(), limit.getValue());
    }
}

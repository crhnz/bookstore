package com.alc.bookstore.bms.books.application.findAll;

import static org.junit.jupiter.api.Assertions.*;

import com.alc.bookstore.bms.books.BooksModuleUnitTest;
import com.alc.bookstore.bms.books.application.BookResponse;
import com.alc.bookstore.bms.books.application.findall.FindAllBooksQuery;
import com.alc.bookstore.bms.books.application.findall.FindAllBooksQueryHandler;
import com.alc.bookstore.bms.books.application.findall.FindAllBooksUC;
import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.bms.books.domain.BookMother;
import com.alc.bookstore.shared.application.PaginatedResponse;
import com.alc.bookstore.shared.domain.ListMother;
import com.alc.bookstore.shared.domain.criteria.Criteria;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class FindAllBooksQueryHandlerTest extends BooksModuleUnitTest {
    FindAllBooksQueryHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new FindAllBooksQueryHandler(new FindAllBooksUC(repository));
    }

    @Test
    void it_should_find_existing_books() {
        // ARRANGE
        List<Book> books = ListMother.random(BookMother::random);
        shouldMatch(Criteria.builder(0, 10).build(), books);

        // ACT
        FindAllBooksQuery query = FindAllBooksQueryMother.empty(0, 10);
        PaginatedResponse<BookResponse> response = handler.handle(query);

        // ASSERT
        PaginatedResponse<BookResponse> expected =
                new PaginatedResponse<>(
                        books.stream().map(BookResponse::fromAggregate).toList(),
                        books.size(),
                        0,
                        10);
        assertEquals(expected, response);
    }

    @Test
    void it_should_thrown_an_exception_if_database_crashes() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    // ARRANGE
                    shouldMatchCrash(
                            Criteria.builder(0, 10).build(),
                            new RuntimeException("Database crash!"));

                    // ACT
                    FindAllBooksQuery query = FindAllBooksQueryMother.empty(0, 10);
                    handler.handle(query);

                    // ASSERT
                    fail("This point should not be reachable");
                });
    }
}

package com.alc.bookstore.bms.books;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;

import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.bms.books.domain.BookRepository;
import com.alc.bookstore.shared.application.UnitTestCase;
import com.alc.bookstore.shared.domain.criteria.Criteria;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public abstract class BooksModuleUnitTest extends UnitTestCase {
    protected BookRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(BookRepository.class);
    }

    public void shouldMatch(final Criteria criteria, final List<Book> books) {
        Mockito.when(repository.matching(argThat(criteria::equals))).thenReturn(books);
    }

    public void shouldMatchCrash(final Criteria criteria, final Exception ex) {
        Mockito.when(repository.matching(argThat(criteria::equals))).thenThrow(ex);
    }

    public void shouldHaveSaved(final Book book) {}
}

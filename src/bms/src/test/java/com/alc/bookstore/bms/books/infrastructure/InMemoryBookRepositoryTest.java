package com.alc.bookstore.bms.books.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.bms.books.domain.BookIDMother;
import com.alc.bookstore.bms.books.domain.BookMother;
import com.alc.bookstore.shared.domain.criteria.Criteria;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryBookRepositoryTest {

    private InMemoryBookRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryBookRepository();
    }

    @Test
    void save_shouldStoreBook() {
        Book book = BookMother.random();

        repository.save(book);

        Optional<Book> found = repository.search(book.getId());
        assertTrue(found.isPresent(), "Book should be found");
        assertEquals(book.getId(), found.get().getId());
        assertEquals(book.getTitle(), found.get().getTitle());
        assertEquals(book.getDescription(), found.get().getDescription());
        assertEquals(book.getGender(), found.get().getGender());
    }

    @Test
    void search_shouldReturnEmptyWhenNotFound() {
        Optional<Book> found = repository.search(BookIDMother.random());

        assertFalse(found.isPresent(), "Should be empty for non-existing ID");
    }

    @Test
    void matching_shouldReturnAllStoredBooks() {
        Book book1 = BookMother.random();
        Book book2 = BookMother.random();

        repository.save(book1);
        repository.save(book2);

        List<Book> books = repository.matching(Criteria.builder(0, 10).build());

        assertEquals(2, books.size(), "Should return 2 books");

        boolean containsBook1 = books.stream().anyMatch(b -> b.getId().equals(book1.getId()));
        boolean containsBook2 = books.stream().anyMatch(b -> b.getId().equals(book2.getId()));

        assertTrue(containsBook1, "Should contain book1");
        assertTrue(containsBook2, "Should contain book2");
    }

    @Test
    void deleteAll_shouldClearAllBooks() {
        repository.save(BookMother.random());
        repository.save(BookMother.random());

        repository.deleteAll();

        List<Book> books = repository.matching(Criteria.builder(0, 10).build());
        assertTrue(books.isEmpty(), "Repository should be empty after deleteAll");
    }
}

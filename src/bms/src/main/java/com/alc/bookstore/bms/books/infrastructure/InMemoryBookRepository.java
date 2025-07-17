package com.alc.bookstore.bms.books.infrastructure;

import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.bms.books.domain.BookRepository;
import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.shared.domain.annotation.RepositoryComponent;
import com.alc.bookstore.shared.domain.criteria.Criteria;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RepositoryComponent
public final class InMemoryBookRepository implements BookRepository {
    private HashMap<String, Book> books = new HashMap<>();

    @Override
    public void save(final Book book) {
        books.put(book.getId().getValue(), book);
    }

    public Optional<Book> search(final BookID id) {
        return Optional.ofNullable(books.get(id.getValue()));
    }

    @Override
    public List<Book> matching(final Criteria criteria) {
        return new LinkedList<>(books.values());
    }

    @Override
    public void deleteAll() {
        books.clear();
    }
}

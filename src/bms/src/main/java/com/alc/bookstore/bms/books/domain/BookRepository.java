package com.alc.bookstore.bms.books.domain;

import com.alc.bookstore.bms.books.domain.vo.BookID;
import com.alc.bookstore.shared.domain.criteria.Criteria;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);

    Optional<Book> search(BookID id);

    List<Book> matching(Criteria criteria);

    void deleteAll();
}

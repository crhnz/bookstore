package com.alc.bookstore.backend.books;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alc.bookstore.bms.books.domain.Book;
import com.alc.bookstore.bms.books.domain.BookMother;
import com.alc.bookstore.bms.books.domain.BookRepository;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FindAllBooksGetControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private BookRepository bookRepository;

    private List<Book> books;

    @BeforeEach
    void setUp() {
        // Limpiar y cargar datos reales
        bookRepository.deleteAll();

        Book book1 = BookMother.random();
        Book book2 = BookMother.random();

        bookRepository.save(book1);
        bookRepository.save(book2);

        books = List.of(book1, book2);
    }

    @Test
    void shouldReturnBooksFromDatabase() throws Exception {
        mockMvc.perform(get("/books").param("offset", "0").param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resources").isArray())
                .andExpect(jsonPath("$.resources.length()").value(2))
                .andExpect(
                        jsonPath("$.resources[*].id")
                                .value(
                                        Matchers.containsInAnyOrder(
                                                books.get(0).getId().getValue(),
                                                books.get(1).getId().getValue())))
                .andExpect(
                        jsonPath("$.resources[*].title")
                                .value(
                                        Matchers.containsInAnyOrder(
                                                books.get(0).getTitle().getValue(),
                                                books.get(1).getTitle().getValue())))
                .andExpect(jsonPath("$.total").value(2));
    }
}

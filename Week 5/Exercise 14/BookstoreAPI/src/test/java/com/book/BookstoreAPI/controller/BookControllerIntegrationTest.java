package com.book.BookstoreAPI.controller;

import com.book.BookstoreAPI.model.Book;
import com.book.BookstoreAPI.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testCreateBook() throws Exception {
        String bookJson = "{\"title\": \"Book1\", \"author\": \"Author1\", \"price\": 111.11, \"isbn\": \"1234599990123\"}";

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Book1"))
                .andExpect(jsonPath("$.author").value("Author1"))
                .andExpect(jsonPath("$.price").value(111.11))
                .andExpect(jsonPath("$.isbn").value("1234599990123"));
    }

    @Test
    void testGetAllBooks() throws Exception {
        Book book1 = new Book(null, "Book1", "Author1", 111.11, "1234599990123");
        Book book2 = new Book(null, "Book2", "Author2", 222.22, "1234599990124");
        bookRepository.save(book1);
        bookRepository.save(book2);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Book1"))
                .andExpect(jsonPath("$[1].title").value("Book2"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book(null, "Book1", "Author1", 111.11, "1234599990123");
        book = bookRepository.save(book);

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book1"))
                .andExpect(jsonPath("$.author").value("Author1"))
                .andExpect(jsonPath("$.price").value(111.11))
                .andExpect(jsonPath("$.isbn").value("1234599990123"));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book(null, "Book1", "Author1", 111.11, "1234599990123");
        book = bookRepository.save(book);

        String updatedBookJson = "{\"title\": \"Book2, 2nd Edition\", \"author\": \"Author2\", \"price\": 150.00, \"isbn\": \"1234567999123\"}";

        mockMvc.perform(put("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book2, 2nd Edition"))
                .andExpect(jsonPath("$.author").value("Author2"))
                .andExpect(jsonPath("$.price").value(150.00))
                .andExpect(jsonPath("$.isbn").value("1234567999123"));
    }

    @Test
    void testDeleteBook() throws Exception {
        Book book = new Book(null, "Book1", "Author1", 111.11, "1234599990123");
        book = bookRepository.save(book);

        mockMvc.perform(delete("/books/" + book.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books/" + book.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBooksByTitleAndAuthor() throws Exception {
        Book book1 = new Book(null, "Book1", "Author1", 111.11, "1234599990123");
        Book book2 = new Book(null, "Book2", "Author2", 222.22, "1234599990124");
        bookRepository.save(book1);
        bookRepository.save(book2);

        mockMvc.perform(get("/books/search?title=Book1&author=Author1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Book1"))
                .andExpect(jsonPath("$[0].author").value("Author1"));
    }
}

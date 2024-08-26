package com.book.BookstoreAPI.repository;

import com.book.BookstoreAPI.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Use H2
class BookRepositoryIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateAndFindBook() {
        Book book = new Book(null, "Book1", "Author1", 111.11, "9994567890123");
        Book savedBook = bookRepository.save(book);

        Book foundBook = bookRepository.findById(savedBook.getId()).orElse(null);

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Book1");
    }
}

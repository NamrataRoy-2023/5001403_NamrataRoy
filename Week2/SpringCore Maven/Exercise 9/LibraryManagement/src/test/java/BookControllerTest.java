// src/test/java/com/example/librarymanagement/BookControllerTest.java

import com.example.LibraryManagementApplication;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @Before
    public void setup() {
        book = new Book("Test Book", "Test Author");
        bookRepository.save(book);
    }

    @Test
    public void testGetAllBooks() {
        ResponseEntity<List> response = restTemplate.exchange("/api/books", HttpMethod.GET, null, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetBookById() {
        ResponseEntity<Book> response = restTemplate.exchange("/api/books/" + book.getId(), HttpMethod.GET, null, Book.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(book.getTitle(), response.getBody().getTitle());
    }

    @Test
    public void testCreateBook() {
        Book newBook = new Book("New Book", "New Author");
        HttpEntity<Book> request = new HttpEntity<>(newBook, new HttpHeaders());
        ResponseEntity<Book> response = restTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newBook.getTitle(), response.getBody().getTitle());
    }

    @Test
    public void testUpdateBook() {
        book.setTitle("Updated Book");
        HttpEntity<Book> request = new HttpEntity<>(book, new HttpHeaders());
        ResponseEntity<Book> response = restTemplate.exchange("/api/books/" + book.getId(), HttpMethod.PUT, request, Book.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(book.getTitle(), response.getBody().getTitle());
    }

    @Test
    public void testDeleteBook() {
        restTemplate.exchange("/api/books/" + book.getId(), HttpMethod.DELETE, null, Void.class);
        ResponseEntity<Book> response = restTemplate.exchange("/api/books/" + book.getId(), HttpMethod.GET, null, Book.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
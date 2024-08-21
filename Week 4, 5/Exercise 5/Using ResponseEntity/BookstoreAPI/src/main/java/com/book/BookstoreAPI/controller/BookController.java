package com.book.BookstoreAPI.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.book.BookstoreAPI.model.Book;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();

    // Get all books with custom headers
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        if (books.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .header("Custom-Header", "NoBooksAvailable")
                    .body(Collections.emptyList());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BooksListed");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(books);
    }

    // Get a book by its ID with custom headers
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookFound");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(book);
    }

    // Create a new book with custom headers
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        books.add(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookCreated");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .body(book);
    }

    // Update an existing book with custom headers
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book existingBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setIsbn(updatedBook.getIsbn());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookUpdated");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(existingBook);
    }

    // Delete a book with custom headers
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book bookToDelete = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        books.remove(bookToDelete);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookDeleted");

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .headers(headers)
                .build();
    }

    // Search books with custom headers
    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        List<Book> filteredBooks = books.stream()
                .filter(book -> (title != null && book.getTitle().equalsIgnoreCase(title)) ||
                        (author != null && book.getAuthor().equalsIgnoreCase(author)))
                .collect(Collectors.toList());

        if (filteredBooks.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "NoBooksFound");

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .headers(headers)
                    .body(Collections.emptyList());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BooksFiltered");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(filteredBooks);
    }
}

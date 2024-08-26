package com.book.BookstoreAPI.controller;

import com.book.BookstoreAPI.model.Book;

import com.book.BookstoreAPI.service.BookService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotNull;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
@Validated
@Tag(name = "Book", description = "Bookstore API implemented with Spring Boot RESTful service using springdoc.")
public class BookController {

        private final BookService bookService;

        public BookController(BookService bookService) {
                this.bookService = bookService;
        }

        @Operation(summary = "Get all books", description = "Get all books from the bookstore")
        @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseStatus(HttpStatus.OK)
        public List<Book> getAllBooks() {
                return bookService.getAllBooks();
        }

        @Operation(summary = "Get book by ID", description = "Get a book from the bookstore by its ID")
        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseStatus(HttpStatus.OK)
        public EntityModel<Book> getBookById(@PathVariable @NotNull Long id) {
                Book book = bookService.getBookById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

                return EntityModel.of(book,
                                linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel(),
                                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
        }

        @Operation(summary = "Search books", description = "Search customers by title or author")
        @GetMapping(value = "/search", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<Book>> getBooks(
                        @RequestParam(required = false) String title,
                        @RequestParam(required = false) String author) {

                List<Book> filteredBooks = bookService.getBooks(title, author);

                if (filteredBooks.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                } else {
                        return ResponseEntity.ok(filteredBooks);
                }
        }

        @Operation(summary = "Create a new book", description = "Create a new book in the bookstore")
        @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
                        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseStatus(HttpStatus.CREATED)
        public Book createBook(@Valid @RequestBody Book book) {
                return bookService.createBook(book);
        }

        @Operation(summary = "Update book by ID", description = "Update a book in the bookstore by its ID")
        @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @ResponseStatus(HttpStatus.OK)
        public Book updateBook(@PathVariable @NotNull Long id, @Valid @RequestBody Book book) {
                return bookService.updateBook(id, book);
        }

        @Operation(summary = "Delete book by ID", description = "Delete a book from the bookstore by its ID")
        @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteBook(@PathVariable @NotNull Long id) {
                bookService.deleteBook(id);
        }
}

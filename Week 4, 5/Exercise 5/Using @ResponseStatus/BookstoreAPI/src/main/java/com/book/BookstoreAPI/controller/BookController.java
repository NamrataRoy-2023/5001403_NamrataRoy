package com.book.BookstoreAPI.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.book.BookstoreAPI.model.Book;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        if (books.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No books available");
        }
        return books;
    }

	@GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
	public Book getBookById(@PathVariable Long id){
            return books.stream()
		.filter(book -> book.getId().equals(id))
		.findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
	}

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Book createBook(@RequestBody Book book){
            books.add(book);
            return book;
        }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book existingBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setIsbn(updatedBook.getIsbn());

        return existingBook;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        Book bookToDelete = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        books.remove(bookToDelete);
    }

        @GetMapping("/search")
        @ResponseStatus(HttpStatus.OK)
        public List<Book> getBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author){
            List<Book> filteredBooks = books.stream()
                    .filter(book -> (title != null && book.getTitle().equalsIgnoreCase(title)) ||
                            (author != null && book.getAuthor().equalsIgnoreCase(author)))
                    .toList();

            if (filteredBooks.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No books available");
            }
            return filteredBooks;
        }
}

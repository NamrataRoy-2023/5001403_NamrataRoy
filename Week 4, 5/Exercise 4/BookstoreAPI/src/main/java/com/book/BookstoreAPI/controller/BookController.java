package com.book.BookstoreAPI.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.book.BookstoreAPI.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();
	
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks(){
            return ResponseEntity.ok(books);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id){
            return books.stream()
		.filter(book -> book.getId().equals(id))
		.findFirst()
		.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
        
        @PostMapping
        public ResponseEntity<Book> createBook(@RequestBody Book book){
            books.add(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }
        
        @PutMapping("/{id}")
        public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
            return books.stream()
                    .filter(book -> book.getId().equals(id))
                    .findFirst()
                    .map(existingBook -> {
                        existingBook.setTitle(updatedBook.getTitle());
                        existingBook.setAuthor(updatedBook.getAuthor());
                        existingBook.setPrice(updatedBook.getPrice());
                        existingBook.setIsbn(updatedBook.getIsbn());
                        return ResponseEntity.ok(existingBook);
                    })
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable Long id){
            boolean removed = books.removeIf(book -> book.getId().equals(id));
            return removed ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        @GetMapping("/search")
        public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author){
            List<Book> filteredBooks = books.stream()
                    .filter(book -> (title != null && book.getTitle().equalsIgnoreCase(title)) ||
                            (author != null && book.getAuthor().equalsIgnoreCase(author)))
                    .toList();

            if (filteredBooks.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else{
                return ResponseEntity.ok(filteredBooks);
            }
        }

}

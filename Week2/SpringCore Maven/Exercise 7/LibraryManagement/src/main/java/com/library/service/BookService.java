package com.library.service;

import com.library.repository.BookRepository;



public class BookService {

    private BookRepository bookRepository;

    // Constructor for constructor injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Setter method for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void printMessage() {
        System.out.println("Hello from BookService!");
        bookRepository.printMessage();
    }
}
package com.library.service;

import com.library.repository.BookRepository;



public class BookService {

    private BookRepository bookRepository;

    // Setter method for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void printMessage() {
        System.out.println("Hello from BookService!");
        bookRepository.printMessage();
    }
}
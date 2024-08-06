package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public void printMessage() {
        System.out.println("Hello from BookRepository!");
    }
}
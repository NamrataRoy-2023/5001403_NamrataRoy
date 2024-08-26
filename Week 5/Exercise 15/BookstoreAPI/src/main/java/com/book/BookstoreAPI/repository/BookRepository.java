package com.book.BookstoreAPI.repository;

import com.book.BookstoreAPI.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByTitleContainingAndAuthorContaining(String title, String author);
}

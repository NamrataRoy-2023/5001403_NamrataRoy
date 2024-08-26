package com.book.BookstoreAPI.repository;

import com.book.BookstoreAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}

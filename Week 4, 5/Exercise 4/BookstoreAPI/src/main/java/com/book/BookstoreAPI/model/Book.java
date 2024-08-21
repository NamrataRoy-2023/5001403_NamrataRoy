package com.book.BookstoreAPI.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private double price;
    private String isbn;
}

package com.book.BookstoreAPI.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @JsonProperty("title")
    private String title;

    @NotNull
    @Size(min = 1, max = 100)
    @JsonProperty("author")
    private String author;

    @Min(value = 0)
    @JsonProperty("price")
    private Double price;

    @NotNull
    @Size(min = 10, max = 13)
    @JsonProperty("isbn")
    private String isbn;

}









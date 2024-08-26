package com.book.BookstoreAPI.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDTO extends RepresentationModel<BookDTO> {
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @JsonProperty("book_title")
    private String title;

    @NotNull(message = "Author cannot be null")
    @Size(min = 2, max = 100, message = "Author must be between 2 and 100 characters")
    @JsonProperty("book_author")
    private String author;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than 0")
    @JsonProperty("book_price")
    private double price;

    @NotNull(message = "ISBN cannot be null")
    @Size(min = 10, max = 50, message = "ISBN must be between 10 and 50 characters")
    @JsonProperty("book_isbn")
    private String isbn;
}

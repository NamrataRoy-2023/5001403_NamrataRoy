package com.book.BookstoreAPI.controller;

import com.book.BookstoreAPI.model.Book;
import com.book.BookstoreAPI.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void createBook_ShouldReturnCreatedBook() throws Exception {
        Book book = new Book(1L, "Hi Baby", "R.K.Roy", 450.00, "9854126541235");

        given(bookService.createBook(any(Book.class))).willReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Hi Baby\", \"author\": \"R.K.Roy\", \"price\": 450.00, \"isbn\": \"9854126541235\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Hi Baby")))
                .andExpect(jsonPath("$.author", is("R.K.Roy")))
                .andExpect(jsonPath("$.price", is(450.00)))
                .andExpect(jsonPath("$.isbn", is("9854126541235")));
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() throws Exception {
        Book book1 = new Book(1L, "Hi Baby", "R.K.Roy", 450.00, "9854126541235");
        Book book2 = new Book(2L, "My Biography", "M.S.", 350.80, "99654122368854");

        List<Book> books = Arrays.asList(book1, book2);

        given(bookService.getAllBooks()).willReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Hi Baby")))
                .andExpect(jsonPath("$[0].author", is("R.K.Roy")))
                .andExpect(jsonPath("$[0].price", is(450.00)))
                .andExpect(jsonPath("$[0].isbn", is("9854126541235")))
                .andExpect(jsonPath("$[1].title", is("My Biography")))
                .andExpect(jsonPath("$[1].author", is("M.S.")))
                .andExpect(jsonPath("$[1].price", is(350.80)))
                .andExpect(jsonPath("$[1].isbn", is("99654122368854")));
    }

    @Test
    void getBookById_ShouldReturnBook() throws Exception {
        Book book = new Book(1L, "Hi Baby", "R.K.Roy", 450.00, "9854126541235");

        given(bookService.getBookById(anyLong())).willReturn(Optional.of(book));

        mockMvc.perform(get("/books/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Hi Baby")))
                .andExpect(jsonPath("$.author", is("R.K.Roy")))
                .andExpect(jsonPath("$.price", is(450.00)))
                .andExpect(jsonPath("$.isbn", is("9854126541235")));
    }

    @Test
    void getBookById_WhenBookNotFound_ShouldReturn404() throws Exception {
        given(bookService.getBookById(anyLong())).willReturn(Optional.empty());

        mockMvc.perform(get("/books/{id}", 3L))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() throws Exception {
        Book book = new Book(1L, "Hi Baby", "R.K.Roy", 450.00, "9854126541235");

        given(bookService.updateBook(anyLong(), any(Book.class))).willReturn(book);

        mockMvc.perform(put("/books/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Hi Baby\", \"author\": \"R.K.Roy\", \"price\": 450.00, \"isbn\": \"9854126541235\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Hi Baby")))
                .andExpect(jsonPath("$.author", is("R.K.Roy")))
                .andExpect(jsonPath("$.price", is(450.00)))
                .andExpect(jsonPath("$.isbn", is("9854126541235")));
    }

    @Test
    void deleteBook_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/books/{id}", 1L))
                .andExpect(status().isNoContent());

        Mockito.verify(bookService).deleteBook(anyLong());
    }
}
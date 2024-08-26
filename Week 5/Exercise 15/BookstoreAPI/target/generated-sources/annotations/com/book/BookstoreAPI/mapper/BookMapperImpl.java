package com.book.BookstoreAPI.mapper;

import com.book.BookstoreAPI.dto.BookDTO;
import com.book.BookstoreAPI.model.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T13:09:06+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO.BookDTOBuilder bookDTO = BookDTO.builder();

        bookDTO.id( book.getId() );
        bookDTO.title( book.getTitle() );
        bookDTO.author( book.getAuthor() );
        if ( book.getPrice() != null ) {
            bookDTO.price( book.getPrice() );
        }
        bookDTO.isbn( book.getIsbn() );

        return bookDTO.build();
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setAuthor( bookDTO.getAuthor() );
        book.setPrice( bookDTO.getPrice() );
        book.setIsbn( bookDTO.getIsbn() );

        return book;
    }
}

package com.book.BookstoreAPI.mapper;

import com.book.BookstoreAPI.dto.BookDTO;
import com.book.BookstoreAPI.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(Book book);
    Book toEntity(BookDTO bookDTO);
}

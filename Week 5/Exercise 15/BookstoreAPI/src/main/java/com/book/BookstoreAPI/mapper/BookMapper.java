package com.book.BookstoreAPI.mapper;

import com.book.BookstoreAPI.dto.BookDTO;
import com.book.BookstoreAPI.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);
}

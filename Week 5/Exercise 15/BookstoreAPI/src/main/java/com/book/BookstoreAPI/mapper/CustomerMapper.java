package com.book.BookstoreAPI.mapper;

import com.book.BookstoreAPI.dto.CustomerDTO;
import com.book.BookstoreAPI.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);
}

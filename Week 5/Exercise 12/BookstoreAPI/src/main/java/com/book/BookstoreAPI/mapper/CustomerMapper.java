package com.book.BookstoreAPI.mapper;

import com.book.BookstoreAPI.dto.CustomerDTO;
import com.book.BookstoreAPI.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}

package com.book.BookstoreAPI.mapper;

import com.book.BookstoreAPI.dto.CustomerDTO;
import com.book.BookstoreAPI.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T13:09:06+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        customerDTO.id( customer.getId() );
        customerDTO.name( customer.getName() );
        customerDTO.email( customer.getEmail() );
        customerDTO.address( customer.getAddress() );

        return customerDTO.build();
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( customerDTO.getId() );
        customer.name( customerDTO.getName() );
        customer.email( customerDTO.getEmail() );
        customer.address( customerDTO.getAddress() );

        return customer.build();
    }
}

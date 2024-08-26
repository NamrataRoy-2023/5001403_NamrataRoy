package com.book.BookstoreAPI.mapper;

import com.book.BookstoreAPI.dto.CustomerDTO;
import com.book.BookstoreAPI.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T12:53:39+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Customer.CustomerBuilder customer1 = Customer.builder();

        customer1.id( customer.getId() );
        customer1.name( customer.getName() );
        customer1.email( customer.getEmail() );
        customer1.address( customer.getAddress() );

        return customer1.build();
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

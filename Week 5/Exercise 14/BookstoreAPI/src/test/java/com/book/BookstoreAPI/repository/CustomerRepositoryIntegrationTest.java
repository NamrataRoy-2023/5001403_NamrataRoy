package com.book.BookstoreAPI.repository;

import com.book.BookstoreAPI.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Use H2
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testCreateAndFindCustomer() {
        Customer customer = new Customer(null, "Test Name", "test@example.com", "Test Address");
        Customer savedCustomer = customerRepository.save(customer);

        Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getName()).isEqualTo("Test Name");
        assertThat(foundCustomer.getEmail()).isEqualTo("test@example.com");
        assertThat(foundCustomer.getAddress()).isEqualTo("Test Address");
    }
}
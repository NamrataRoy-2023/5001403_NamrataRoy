package com.book.BookstoreAPI.controller;

import com.book.BookstoreAPI.dto.CustomerDTO;
import com.book.BookstoreAPI.exception.CustomerNotFoundException;
import com.book.BookstoreAPI.mapper.CustomerMapper;
import com.book.BookstoreAPI.model.Customer;
import com.book.BookstoreAPI.repository.CustomerRepository;
import com.book.BookstoreAPI.service.CustomerService;

import io.micrometer.core.instrument.Timer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import io.micrometer.core.instrument.MeterRegistry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@Validated
@Tag(name = "Customer", description = "Bookstore API implemented with Spring Boot RESTful service using springdoc.")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get all customers", description = "Get all customers from the bookstore")
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Operation(summary = "Get customer by ID", description = "Get a customer by ID from the bookstore")
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomerById(@PathVariable @NotNull Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Add a customer", description = "Add a customer to the bookstore")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @Operation(summary = "Search customers", description = "Search customers by name or email")
    @GetMapping(value = "/search", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        List<Customer> filteredCustomers = customerService.getCustomers(name, email);

        if (filteredCustomers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(filteredCustomers);
        }
    }

    @Operation(summary = "Update customer by ID", description = "Update a customer in the bookstore by its ID")
    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@PathVariable @NotNull Long id, @Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @Operation(summary = "Delete customer by ID", description = "Delete a customer from the bookstore by its ID")
    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable @NotNull Long id) {
        customerService.deleteCustomer(id);
    }

}
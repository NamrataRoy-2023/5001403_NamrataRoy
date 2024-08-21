package com.book.BookstoreAPI.controller;

import com.book.BookstoreAPI.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private List<Customer> customers = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customer.setId((long) (customers.size()+1));
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> customerRegistration(@RequestParam String name, @RequestParam String email, @RequestParam String address){
        Customer customer = new Customer();
        customer.setId((long) (customers.size()+1));
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customers);
    }
}

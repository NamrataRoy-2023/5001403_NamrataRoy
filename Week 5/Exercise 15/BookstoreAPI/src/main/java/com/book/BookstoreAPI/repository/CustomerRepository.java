package com.book.BookstoreAPI.repository;

import com.book.BookstoreAPI.model.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

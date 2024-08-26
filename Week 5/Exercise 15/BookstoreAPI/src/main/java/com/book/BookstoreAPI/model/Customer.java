package com.book.BookstoreAPI.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
}

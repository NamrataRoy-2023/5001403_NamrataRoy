package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor

// Defining a named query using the @NamedQueries annotation
@NamedQueries({
        @NamedQuery(name = "Department.findByLocation",
                query = "SELECT d FROM Department d WHERE d.location = :location"),
        @NamedQuery(name = "Department.findByManager",
                query = "SELECT d FROM Department d WHERE d.manager = :manager")
})

public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;



}
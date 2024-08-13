package com.example.repository;

import com.example.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Using keywords in method names to create custom query methods
    List<Department> findByNameContaining(String name);

    Department findByName(String name);

    // Implementing custom query methods using the @Query annotation
    @Query("SELECT d FROM Department d WHERE d.name LIKE %:name%")
    List<Department> findDepartmentsByName(@Param("name") String name);


    List<Department> findByLocation(@Param("location") String location);

    List<Department> findByManager(@Param("manager") String manager);
}
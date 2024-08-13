package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQuery;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Using keywords in method names to create custom query methods
    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByEmailContaining(String email);

    Employee findByName(String name);

    // Implementing custom query methods using the @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId AND e.email LIKE %:email%")
    List<Employee> findEmployeesByDepartmentIdAndEmail(@Param("departmentId") Long departmentId, @Param("email") String email);

    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);
}

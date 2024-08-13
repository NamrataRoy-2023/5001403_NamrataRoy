package com.example.repository;

import com.example.entity.Employee;
import com.example.projection.EmployeeProjection;
import com.example.projection.EmployeeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Using keywords in method names to create custom query methods
    List<Map<String, Object>> findByDepartmentId(Long departmentId);

    List<Employee> findByEmailContaining(String email);

    Employee findByName(String name);


    List<Map<String, Object>> findAllEmployees();

    // Implementing custom query methods using the @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId AND e.email LIKE %:email%")
    List<Employee> findEmployeesByDepartmentIdAndEmail(@Param("departmentId") Long departmentId, @Param("email") String email);

    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name% OR e.email LIKE %:email%")
    List<Employee> findEmployeesByNameOrEmail(@Param("name") String name, @Param("email") String email);

    @Query("SELECT e.id AS id, e.name AS name, e.email AS email FROM Employee e")
    List<EmployeeProjection> findEmployeeProjections();

    @Query("SELECT new com.example.projection.EmployeeSummary(e.id, e.name, e.email) FROM Employee e")
    List<EmployeeSummary> findEmployeeSummaries(@Param("name") String name);
}


package com.example.repository;

import com.example.entity.Employee;
import com.example.projection.EmployeeProjection;
import com.example.projection.EmployeeSummary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepositoryImpl(@Qualifier("primaryDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Map<String, Object>> findAllEmployees() {
        return jdbcTemplate.queryForList("SELECT * FROM employees");
    }

    @Override
    public List<Employee> findEmployeesByDepartmentIdAndEmail(Long departmentId, String email) {
        return List.of();
    }

    @Override
    public List<Employee> findByDepartmentName(String departmentName) {
        return List.of();
    }

    @Override
    public List<Employee> findEmployeesByNameOrEmail(String name, String email) {
        return List.of();
    }

    @Override
    public List<EmployeeProjection> findEmployeeProjections() {
        return List.of();
    }

    @Override
    public List<EmployeeSummary> findEmployeeSummaries(String name) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByDepartmentId(Long departmentId) {
        return jdbcTemplate.queryForList("SELECT * FROM employees WHERE department_id = ?", departmentId);
    }

    @Override
    public List<Employee> findByEmailContaining(String email) {
        return List.of();
    }

    @Override
    public Employee findByName(String name) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Employee> findAllById(Iterable<Long> iterable) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public void deleteAll(Iterable<? extends Employee> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Employee> S save(S s) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> saveAll(Iterable<S> iterable) {
        return List.of();
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Employee> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Employee> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Employee getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Employee> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Employee> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Employee> boolean exists(Example<S> example) {
        return false;
    }
}
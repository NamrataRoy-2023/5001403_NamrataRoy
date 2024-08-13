package com.example.repository;

import com.example.entity.Department;
import com.example.projection.DepartmentProjection;
import com.example.projection.DepartmentSummary;
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
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public DepartmentRepositoryImpl(@Qualifier("secondaryDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Department> findByNameContaining(String name) {
        return List.of();
    }

    @Override
    public Department findByName(String name) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findAllDepartments() {
        return jdbcTemplate.queryForList("SELECT * FROM departments");
    }

    @Override
    public List<Department> findDepartmentsByName(String name) {
        return List.of();
    }

    @Override
    public List<Department> findByLocation(String location) {
        return List.of();
    }

    @Override
    public List<Department> findByManager(String manager) {
        return List.of();
    }

    @Override
    public List<DepartmentProjection> findDepartmentProjections() {
        return List.of();
    }

    @Override
    public List<DepartmentSummary> findDepartmentSummaries(String name) {
        return List.of();
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }

    @Override
    public List<Department> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Department> findAllById(Iterable<Long> iterable) {
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
    public void delete(Department department) {

    }

    @Override
    public void deleteAll(Iterable<? extends Department> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Department> S save(S s) {
        return null;
    }

    @Override
    public <S extends Department> List<S> saveAll(Iterable<S> iterable) {
        return List.of();
    }

    @Override
    public Optional<Department> findById(Long aLong) {
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
    public <S extends Department> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Department> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Department getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Department> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Department> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Department> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Department> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Department> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Department> boolean exists(Example<S> example) {
        return false;
    }
}
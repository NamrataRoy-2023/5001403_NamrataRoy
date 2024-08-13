package com.example.projection;

public class DepartmentSummary {
    private final Long id;
    private final String name;
    private final String location;

    public DepartmentSummary(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
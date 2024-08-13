package com.example.projection;

import org.springframework.beans.factory.annotation.Value;

public interface DepartmentProjection {
    @Value("#{target.id}")
    Long getId();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.location}")
    String getLocation();
}
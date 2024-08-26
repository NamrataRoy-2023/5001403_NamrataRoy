package com.book.BookstoreAPI.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class CustomMetrics {

    @Autowired
    private MeterRegistry meterRegistry;

    @PostConstruct
    public void init() {
        // Create a custom counter metric
        meterRegistry.counter("custom.metric.book.count");

        // Create other custom metrics as needed
        meterRegistry.gauge("custom.metric.book.gauge", Math.random() * 100);
    }
}

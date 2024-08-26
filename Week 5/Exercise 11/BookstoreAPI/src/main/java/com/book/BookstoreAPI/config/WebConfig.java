package com.book.BookstoreAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorParameter(true) // Enable content type based on request parameter
                .parameterName("mediaType") // The parameter name in the request URL (e.g., ?mediaType=xml)
                .ignoreAcceptHeader(false) // Respect the Accept header if present
                .useRegisteredExtensionsOnly(false) // Allow the use of extensions (e.g., .json or .xml in the URL)
                .defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON) // Default content type
                .mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON) // Map "json" to application/json
                .mediaType("xml", org.springframework.http.MediaType.APPLICATION_XML); // Map "xml" to application/xml
    }
}

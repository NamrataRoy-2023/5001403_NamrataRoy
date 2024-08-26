package com.book.BookstoreAPI.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {

        String securityScheme = "bearerAuth";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securityScheme))
                .components(new Components()
                        .addSecuritySchemes(securityScheme,
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
                                        .name("Authorization")))
                .info(new Info().title("Bookstore API")
                        .description(
                                "Bookstore API implemented with Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                        .version("v1.0.6")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/")

                )
                .externalDocs(new ExternalDocumentation()
                        .description("BookstoreAPI Documentation")
                        .url("http://localhost:8080/swagger-ui/index.html#/"))

        ;

    }

}

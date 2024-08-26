package com.book.BookstoreAPI.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtRequest {
    private String username;
    private String password;
}

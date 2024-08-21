package com.book.BookstoreAPI.dto;

import com.book.BookstoreAPI.serializer.CustomDateSerializer;
import com.book.BookstoreAPI.deserializer.CustomDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;


public class SomeDTO {
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date someDate;

    // Other fields and methods
}

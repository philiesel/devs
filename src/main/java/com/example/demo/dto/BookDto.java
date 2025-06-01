package com.example.demo.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String category;
    private Set<String> nameAuthors;
}

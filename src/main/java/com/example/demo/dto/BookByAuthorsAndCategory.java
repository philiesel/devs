package com.example.demo.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookByAuthorsAndCategory {
    private String category;
    private Set<String> nameAuthors = new HashSet<>();
}

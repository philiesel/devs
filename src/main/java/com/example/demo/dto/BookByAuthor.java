package com.example.demo.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookByAuthor {
    private Set<String> nameAuthors = new HashSet<>();
}

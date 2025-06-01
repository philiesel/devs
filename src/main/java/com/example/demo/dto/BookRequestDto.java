package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookRequestDto {
    @NotNull(message = "Название книги не может быть пустым")
    private String titleBook;
    @NotNull(message = "Название категории книги не может быть пустым")
    private String nameCategory;
    @NotNull(message = "Автор книги не может быть пустым")
    private Set<String> nameAuthors = new HashSet<>();
}

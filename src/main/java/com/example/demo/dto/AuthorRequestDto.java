package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorRequestDto {
    @NotNull(message = "Имя автора не может быть пустым")
    private String name;
}

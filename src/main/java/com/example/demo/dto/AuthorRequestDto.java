package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorRequestDto {
    @NotNull
    private String name;
}

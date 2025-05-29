package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotNull
    private String titleBook;
    @NotNull
    private String nameCategory;
}

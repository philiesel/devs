package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface CategoryService {
    CategoryEntity createCategory(String nameCategory);

    CategoryEntity getById(Long id);

}

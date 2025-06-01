package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;

public interface CategoryService {
    CategoryEntity createCategory(String nameCategory);

    CategoryEntity getById(Long id);

    CategoryEntity findOrCreate(String nameCategory);
}

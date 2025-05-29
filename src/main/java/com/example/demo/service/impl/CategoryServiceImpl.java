package com.example.demo.service.impl;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryEntity createCategory(String categoryName) {
        log.debug("Создание категории с именем {}", categoryName);
        CategoryEntity categoryNew = new CategoryEntity();
        categoryNew.setName(categoryName);
        CategoryEntity saveCategory = categoryRepository.save(categoryNew);
        log.debug("Категория создана: {}", saveCategory.getName());
        return saveCategory;
    }

    public CategoryEntity getById(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));
        return category;
    }

    public CategoryEntity findOrCreate(String nameCategory) {
        Optional<CategoryEntity> category = categoryRepository.findByName(nameCategory);
        return category.orElseGet(() -> createCategory(nameCategory));
    }
}

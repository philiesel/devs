package com.example.demo.dto.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(CategoryEntity entity);
    CategoryEntity toEntity(CategoryDto dto);
}

package com.example.demo.dto.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "category.name", target = "category")
    BookDto toCreatedDto(BookEntity entity);

    @Mapping(source = "category", target = "category.name")
    BookEntity toEntity(BookDto dto);
}

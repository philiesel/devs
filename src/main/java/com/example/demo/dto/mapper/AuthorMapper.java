package com.example.demo.dto.mapper;

import com.example.demo.dto.AuthorDto;
import com.example.demo.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(source = "category.name", target = "category")
    AuthorDto toDto(AuthorEntity entity);
}

package com.example.demo.dto.mapper;

import com.example.demo.dto.AuthorDto;
import com.example.demo.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto toDto(AuthorEntity entity);
}

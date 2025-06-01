package com.example.demo.dto.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.AuthorEntity;
import com.example.demo.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "category.name", target = "category")
    @Mapping(target = "nameAuthors", expression = "java(mapAuthors(book.getAuthors()))")
    BookDto toCreatedDto(BookEntity book);

    default Set<String> mapAuthors(Set<AuthorEntity> authors) {
        return authors.stream()
                .map(AuthorEntity::getName)
                .collect(Collectors.toSet());
    }
}

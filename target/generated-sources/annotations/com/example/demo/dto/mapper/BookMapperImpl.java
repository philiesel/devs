package com.example.demo.dto.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookRequestDto;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.CategoryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-29T10:13:21+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookEntity toEntity(BookRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.setCategory( bookRequestDtoToCategoryEntity( dto ) );

        return bookEntity;
    }

    @Override
    public BookRequestDto toDto(BookEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookRequestDto bookRequestDto = new BookRequestDto();

        bookRequestDto.setNameCategory( entityCategoryName( entity ) );

        return bookRequestDto;
    }

    @Override
    public BookDto toCreatedDto(BookEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setCategory( entityCategoryName( entity ) );
        bookDto.setId( entity.getId() );
        bookDto.setTitle( entity.getTitle() );

        return bookDto;
    }

    @Override
    public BookEntity toEntity(BookDto dto) {
        if ( dto == null ) {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.setCategory( bookDtoToCategoryEntity( dto ) );
        bookEntity.setId( dto.getId() );
        bookEntity.setTitle( dto.getTitle() );

        return bookEntity;
    }

    protected CategoryEntity bookRequestDtoToCategoryEntity(BookRequestDto bookRequestDto) {
        if ( bookRequestDto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( bookRequestDto.getNameCategory() );

        return categoryEntity;
    }

    private String entityCategoryName(BookEntity bookEntity) {
        CategoryEntity category = bookEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }

    protected CategoryEntity bookDtoToCategoryEntity(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( bookDto.getCategory() );

        return categoryEntity;
    }
}

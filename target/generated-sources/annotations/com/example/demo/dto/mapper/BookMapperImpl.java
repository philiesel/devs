package com.example.demo.dto.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.CategoryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T18:41:07+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toCreatedDto(BookEntity book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setCategory( bookCategoryName( book ) );
        bookDto.setId( book.getId() );
        bookDto.setTitle( book.getTitle() );

        bookDto.setNameAuthors( mapAuthors(book.getAuthors()) );

        return bookDto;
    }

    private String bookCategoryName(BookEntity bookEntity) {
        CategoryEntity category = bookEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}

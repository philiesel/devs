package com.example.demo.dto.mapper;

import com.example.demo.dto.AuthorDto;
import com.example.demo.entity.AuthorEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T23:09:37+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDto toDto(AuthorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId( entity.getId() );
        authorDto.setName( entity.getName() );

        return authorDto;
    }
}

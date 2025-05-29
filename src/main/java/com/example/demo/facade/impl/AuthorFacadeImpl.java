package com.example.demo.facade.impl;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorRequestDto;
import com.example.demo.dto.mapper.AuthorMapper;
import com.example.demo.entity.AuthorEntity;
import com.example.demo.facade.AuthorFacade;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorFacadeImpl implements AuthorFacade {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto createAuthor(AuthorRequestDto authorRequestDto) {
        String authorName = authorRequestDto.getName();
        AuthorEntity savedAuthor = authorService.createAuthor(authorName);
        return authorMapper.toDto(savedAuthor);
    }

    @Override
    public AuthorDto getAuthor(Long id) {
        AuthorEntity authorEntity = authorService.getAuthor(id);
        return authorMapper.toDto(authorEntity);
    }

    @Override
    public AuthorDto updateAuthor(Long id, AuthorRequestDto authorRequestDto) {
        String authorName = authorRequestDto.getName();
        AuthorEntity authorUpdated = authorService.updateAuthor(id, authorName);
        return authorMapper.toDto(authorUpdated);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorService.deleteAuthor(id);
    }
}

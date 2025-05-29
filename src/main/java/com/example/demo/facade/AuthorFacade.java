package com.example.demo.facade;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorRequestDto;

public interface AuthorFacade {
    AuthorDto createAuthor(AuthorRequestDto author);

    AuthorDto getAuthor(Long id);

    AuthorDto updateAuthor(Long id, AuthorRequestDto authorRequestDto);

    void deleteAuthor(Long id);
}

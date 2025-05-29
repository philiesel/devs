package com.example.demo.service;

import com.example.demo.entity.AuthorEntity;

public interface AuthorService {

    AuthorEntity createAuthor(String name);

    AuthorEntity getAuthor(Long id);

    AuthorEntity updateAuthor(Long id, String name);

    void deleteAuthor(Long id);
}

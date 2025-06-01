package com.example.demo.service.impl;

import com.example.demo.entity.AuthorEntity;
import com.example.demo.exceptions.AuthorNotFoundException;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorEntity createAuthor(String nameAuthor) {
        AuthorEntity author = new AuthorEntity();
        author.setName(nameAuthor);
        return authorRepository.save(author);
    }

    public AuthorEntity getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Автор с таким %s не найден", id)));
    }

    public AuthorEntity updateAuthor(Long id, String name) {
        AuthorEntity authorEntity = getAuthor(id);
        authorEntity.setName(name);
        return authorRepository.save(authorEntity);
    }

    public void deleteAuthor(Long id) {
        AuthorEntity authorEntity = getAuthor(id);
        authorRepository.delete(authorEntity);
    }

    @Override
    public AuthorEntity findOrCreate(String nameAuthor) {
        Optional<AuthorEntity> author = authorRepository.findByName(nameAuthor);
        return author.orElseGet(() -> createAuthor(nameAuthor));
    }
}

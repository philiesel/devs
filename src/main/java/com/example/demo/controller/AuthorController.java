package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorRequestDto;
import com.example.demo.facade.AuthorFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorFacade authorFacade;

    @PostMapping("/create")
    public ResponseEntity<AuthorDto> createAuthor(
            @RequestBody AuthorRequestDto authorRequestDto) {
        AuthorDto authorCreated = authorFacade.createAuthor(authorRequestDto);
        return new ResponseEntity<>(authorCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(
            @PathVariable Long id) {
        AuthorDto authorDto = authorFacade.getAuthor(id);
        return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(
            @RequestBody AuthorRequestDto authorRequestDto,
            @PathVariable Long id) {
        AuthorDto authorUpdated = authorFacade.updateAuthor(id, authorRequestDto);
        return new ResponseEntity<>(authorUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(
            @PathVariable Long id) {
        authorFacade.deleteAuthor(id);
        return new ResponseEntity<>("Автор успешно удален", HttpStatus.OK);
    }
}

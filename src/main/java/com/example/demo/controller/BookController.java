package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookRequestDto;
import com.example.demo.facade.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {
    private final BookFacade bookFacade;

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(
            @RequestBody BookRequestDto book) {
        BookDto bookCreated = bookFacade.createBook(book);
        return new ResponseEntity<>(bookCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(
            @PathVariable Long id
    ) {
        BookDto bookDto = bookFacade.getBook(id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @RequestBody BookRequestDto bookRequestDto,
            @PathVariable Long id
    ) {
        BookDto bookUpdate = bookFacade.updateBook(id, bookRequestDto);
        return new ResponseEntity<>(bookUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable Long id
    ) {
        bookFacade.deleteBook(id);
        return new ResponseEntity<>("Книга успешно удалена", HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable Long authorId) {
        List<BookDto> books = bookFacade.getBooksByAuthor(authorId);
        return ResponseEntity.ok(books);
    }
}

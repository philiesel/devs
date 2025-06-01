package com.example.demo.facade.impl;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookRequestDto;
import com.example.demo.dto.mapper.BookMapper;
import com.example.demo.entity.BookEntity;
import com.example.demo.service.BookService;
import com.example.demo.facade.BookFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookFacadeImpl implements BookFacade {
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookDto createBook(BookRequestDto book) {
        String titleBook = book.getTitleBook();
        String nameCategory = book.getNameCategory();
        Set<String> authors = book.getNameAuthors();
        BookEntity savedBook = bookService.createBook(titleBook, nameCategory, authors);
        return bookMapper.toCreatedDto(savedBook);
    }

    public BookDto getBook(Long id) {
        BookEntity book = bookService.getBook(id);
        return bookMapper.toCreatedDto(book);
    }

    public BookDto updateBook(Long id, BookRequestDto bookDto) {
        String titleBook = bookDto.getTitleBook();
        BookEntity bookUpdated = bookService.updateBook(id, titleBook);
        return bookMapper.toCreatedDto(bookUpdated);
    }

    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }

    public List<BookDto> getBooksByAuthor(Long id) {
        List<BookEntity> books = bookService.getBooksByAuthor(id);
        return books.stream()
                .map(bookMapper::toCreatedDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findBooksByAuthorsAndCategory(Set<String> nameAuthor, String nameCategory) {
        List<BookEntity> books = bookService.findBooksByAuthorsAndCategory(nameAuthor, nameCategory);
        return books.stream()
                .map(bookMapper::toCreatedDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findByAuthorsName(Set<String> authorName) {
        List<BookEntity> books = bookService.findByAuthorsName(authorName);
        return books.stream()
                .map(bookMapper::toCreatedDto)
                .collect(Collectors.toList());
    }
}

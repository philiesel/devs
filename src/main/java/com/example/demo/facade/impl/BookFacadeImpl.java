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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookFacadeImpl implements BookFacade {
    private final BookService bookServiceImpl;
    private final BookMapper bookMapper;

    public BookDto createBook(BookRequestDto book) {
        String titleBook = book.getTitleBook();
        String nameCategory = book.getNameCategory();
        BookEntity savedBook = bookServiceImpl.createBook(titleBook, nameCategory);
        return bookMapper.toCreatedDto(savedBook);
    }

    public BookDto getBook(Long id) {
        BookEntity book = bookServiceImpl.getBook(id);
        return bookMapper.toCreatedDto(book);
    }

    public BookDto updateBook(Long id, BookRequestDto bookDto) {
        String titleBook = bookDto.getTitleBook();
        String categoryBook = bookDto.getNameCategory();
        BookEntity bookUpdated = bookServiceImpl.updateBook(id, titleBook, categoryBook);
        return bookMapper.toCreatedDto(bookUpdated);
    }

    public void deleteBook(Long id) {
        bookServiceImpl.deleteBook(id);
    }

    public List<BookDto> getBooksByAuthor(Long id) {
        List<BookEntity> books = bookServiceImpl.getBooksByAuthor(id);
        return books.stream()
                .map(bookMapper::toCreatedDto)
                .collect(Collectors.toList());
    }
}

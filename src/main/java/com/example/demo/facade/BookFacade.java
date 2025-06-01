package com.example.demo.facade;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookRequestDto;

import java.util.List;
import java.util.Set;

public interface BookFacade {
    BookDto createBook(BookRequestDto book);

    BookDto getBook(Long id);

    BookDto updateBook(Long id, BookRequestDto bookDto);

    void deleteBook(Long id);

    List<BookDto> getBooksByAuthor(Long authorId);

    List<BookDto> findBooksByAuthorsAndCategory(Set<String> nameAuthor, String nameCategory);

    List<BookDto> findByAuthorsName(Set<String> authorName);
}

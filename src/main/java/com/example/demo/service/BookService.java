package com.example.demo.service;

import com.example.demo.entity.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(String titleBook, String category);

    BookEntity getBook(Long id);

    void deleteBook(Long id);

    BookEntity updateBook(Long id, String title, String category);

    List<BookEntity> getBooksByAuthor(Long id);
}

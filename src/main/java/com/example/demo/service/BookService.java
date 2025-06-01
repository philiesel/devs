package com.example.demo.service;

import com.example.demo.entity.BookEntity;
import java.util.List;
import java.util.Set;

public interface BookService {
    BookEntity createBook(String titleBook, String category, Set<String> nameAuthors);

    BookEntity getBook(Long id);

    void deleteBook(Long id);

    BookEntity updateBook(Long id, String title);

    List<BookEntity> getBooksByAuthor(Long id);

    List<BookEntity> findBooksByAuthorsAndCategory(Set<String> nameAuthor, String nameCategory);

    List<BookEntity> findByAuthorsName(Set<String> authorName);

}

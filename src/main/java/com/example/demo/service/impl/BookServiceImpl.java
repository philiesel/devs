package com.example.demo.service.impl;

import com.example.demo.entity.BookEntity;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryServiceImpl categoryServiceImpl;

    public BookEntity createBook(String titleBook, String category) {
        log.debug("Создание книги с именем {}", titleBook);
        BookEntity createBook = new BookEntity();
        createBook.setTitle(titleBook);
        CategoryEntity categoryEntity = categoryServiceImpl.findOrCreate(category);
        createBook.setCategory(categoryEntity);
        BookEntity saveBook = bookRepository.save(createBook);
        log.debug("Книга создана: {}", saveBook.getTitle());
        return saveBook;
    }

    public BookEntity getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(String.format("Книга с таким %s не найдена", id)));
    }

    public void deleteBook(Long id) {
        bookRepository.delete(getBook(id));
    }

    public BookEntity updateBook(Long id, String title, String category) {
        BookEntity book = getBook(id);
        book.setTitle(title);
        CategoryEntity categoryEntity = categoryServiceImpl.findOrCreate(category);
        book.setCategory(categoryEntity);
        return bookRepository.save(book);
    }

    public List<BookEntity> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorsId(authorId);
    }
}

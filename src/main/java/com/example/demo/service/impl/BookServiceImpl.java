package com.example.demo.service.impl;

import com.example.demo.entity.AuthorEntity;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Transactional
    public BookEntity createBook(String titleBook, String category, Set<String> nameAuthors) {
        log.debug("Создание книги с именем {}", titleBook);
        BookEntity createBook = new BookEntity();
        createBook.setTitle(titleBook);
        CategoryEntity categoryEntity = categoryService.findOrCreate(category);
        createBook.setCategory(categoryEntity);
        Set<AuthorEntity> authors = nameAuthors.stream().map(authorService::findOrCreate).collect(Collectors.toSet());
        createBook.getAuthors().addAll(authors);
        BookEntity saveBook = bookRepository.save(createBook);
        log.debug("Книга создана: {}", saveBook.getTitle());
        return saveBook;
    }


    public BookEntity getBook(Long id) {
        log.debug("Книга с ID: {}", id);
        return bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(String.format("Книга с таким %s не найдена", id)));
    }

    public void deleteBook(Long id) {
        log.debug("Удалить книгу с ID: {}", id);
        bookRepository.delete(getBook(id));
    }

    public BookEntity updateBook(Long id, String title) {
        log.debug("Обновить книгу с ID: {} новое название: {}", id, title);
        BookEntity book = getBook(id);
        book.setTitle(title);
        return bookRepository.save(book);
    }

    public List<BookEntity> getBooksByAuthor(Long authorId) {
        log.debug("Список книг автора с ID: {}", authorId);
        return bookRepository.findByAuthorsId(authorId);
    }

    public List<BookEntity> findBooksByAuthorsAndCategory(Set<String> nameAuthor, String nameCategory) {
        log.debug("Список книг с авторами: {} из категории: {}", nameAuthor, nameCategory);
        return bookRepository.findBooksByAuthorsAndCategory(nameAuthor, nameCategory);
    }

    public List<BookEntity> findByAuthorsName(Set<String> authorName) {
        log.debug("Список книг с авторами: {}", authorName);
        return bookRepository.findByAuthorsName(authorName);
    }
}

package com.example.demo;

import com.example.demo.dto.BookByAuthorsAndCategory;
import com.example.demo.dto.BookRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = "/updateTestBook.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class BookControllerTest {

    @Test
    @Rollback
    void createBookShouldReturnBookDto() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                             "titleBook" : "Дом",
                             "nameCategory" : "Повесть",
                             "nameAuthors" : ["Дюма", "Стивен Кинг"]
                        }
                        """)
                .when()
                .post("api/v1/books/create")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", notNullValue(),
                        "title", is("Дом"),
                        "category", is("Повесть"));
    }

    @Test
    void getBookShouldReturnBookWhenBookExists() {
        Long bookId = 3L;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/books/" + bookId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(bookId.intValue()),
                        "title", notNullValue(),
                        "category", notNullValue());
    }

    @Test
    void whenDeleteBookThenStatus200() {
        Long bookId = 1L;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/v1/books/" + bookId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("Книга успешно удалена"));
    }

    @Test
    void whenUpdateBookThenStatus200() {
        Long bookId = 3L;

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setTitleBook("Молчание");
        bookRequestDto.setNameCategory("Сказка");
        bookRequestDto.getNameAuthors().add("Стивен Кинг");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(bookRequestDto)
                .when()
                .patch("/api/v1/books/" + bookId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", notNullValue())
                .body("category", notNullValue());
    }

    @Test
    void whenUpdateNonExistentBookThenStatus404() {
        Long bookId = 999L;

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setTitleBook("Матрица");
        bookRequestDto.setNameCategory("Фантастика");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(bookRequestDto)
                .when()
                .patch("/api/v1/books/" + bookId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo(String.format("Книга с таким %s не найдена", bookId)));
    }

    @Test
    void findBooksByAuthorsAndCategoryThenStatus200() {
        BookByAuthorsAndCategory book = new BookByAuthorsAndCategory();
        book.getNameAuthors().add("Дюма");
        book.setCategory("Повесть");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .get("/api/v1/books/filter/author-category")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].title", equalTo("Дом"));
    }
}
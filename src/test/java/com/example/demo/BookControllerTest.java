package com.example.demo;

import com.example.demo.dto.BookRequestDto;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookControllerTest {

    @Test
    void createBookShouldReturnBookDto() {
        RestAssured
                .given()
                .contentType("application/json;charset=UTF-8")
                .body("""
                        {
                            "titleBook": "Сияние",
                            "nameCategory": "Сказка"
                        }
                        """)
                .when()
                .post("http://localhost:8080/api/v1/books/create")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", is(1),
                        "title", is("Сияние"));
    }

    @Test
    @Sql(scripts = "/insert-test-book.sql")
    void getBookShouldReturnBookWhenBookExists() {
        long bookId = 2L;

        RestAssured
                .given()
                .contentType("application/json;charset=UTF-8")
                .when()
                .get("/api/v1/books/" + bookId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo((int) bookId))
                .body("title", notNullValue())
                .body("category", notNullValue());
    }

    @Test
    void whenDeleteBookThenStatus200() {
        Long bookId = 1L;

        RestAssured
                .given()
                .contentType("application/json;charset=UTF-8")
                .when()
                .delete("/api/v1/books/" + bookId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("Книга успешно удалена"));
    }

    @Test
    @Sql(scripts = "/updateTestBook.sql")
    void whenUpdateBookThenStatus200() {
        Long bookId = 3L;

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setTitleBook("Старик и море");
        bookRequestDto.setNameCategory("Сказка");

        RestAssured
                .given()
                .contentType("application/json;charset=UTF-8")
                .body(bookRequestDto)
                .when()
                .patch("/api/v1/books/" + bookId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo("Старик и море"))
                .body("category", equalTo("Сказка"));
    }

    @Test
    void whenUpdateNonExistentBookThenStatus404() {
        Long bookId = 999L;

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setTitleBook("Матрица");
        bookRequestDto.setNameCategory("Фантастика");

        RestAssured
                .given()
                .contentType("application/json;charset=UTF-8")
                .body(bookRequestDto)
                .when()
                .patch("/api/v1/books/" + bookId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo(String.format("Книга с таким %s не найдена", bookId)));
    }
}
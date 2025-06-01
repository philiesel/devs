package com.example.demo.repository;

import com.example.demo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByAuthorsId(Long authorId);

    @Query("SELECT b FROM BookEntity b " +
            "JOIN b.authors a " +
            "WHERE a.name IN :authors " +
            "AND b.category.name = :categoryName")
    List<BookEntity> findBooksByAuthorsAndCategory(
            @Param("authors") Set<String> authors,
            @Param("categoryName") String categoryName);

    @Query("SELECT b FROM BookEntity b JOIN b.authors a WHERE a.name IN :names")
    List<BookEntity> findByAuthorsName(Set<String> authorName);
}

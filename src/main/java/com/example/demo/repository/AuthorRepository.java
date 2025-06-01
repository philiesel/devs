package com.example.demo.repository;

import com.example.demo.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    @NonNull
    Optional<AuthorEntity> findById(@NonNull Long id);

    Optional<AuthorEntity> findByName(String nameAuthor);
}

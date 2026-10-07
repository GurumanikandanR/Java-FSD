package com.librarymanagement.repository;

import com.librarymanagement.model.Author;
import com.librarymanagement.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public class AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Author> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class,id));
    }
}

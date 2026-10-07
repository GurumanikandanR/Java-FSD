package com.librarymanagement.repository;

import com.librarymanagement.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Book book) {
        entityManager.persist(book);
    }

    public List<Book> findAll() {
        String jpql="select b from Book b";

        return entityManager
                .createQuery(jpql,Book.class)
                .getResultList()                ;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class,id));
    }
}

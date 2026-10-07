package com.librarymanagement.repository;

import com.librarymanagement.model.Book;
import com.librarymanagement.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Member> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class,id));
    }
}

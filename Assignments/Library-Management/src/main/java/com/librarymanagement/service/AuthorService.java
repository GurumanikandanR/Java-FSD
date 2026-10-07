package com.librarymanagement.service;

import com.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.model.Author;
import com.librarymanagement.model.Book;
import com.librarymanagement.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getById(Long id) {
        Optional<Author> optional =authorRepository.getById(id);
        if(optional.isEmpty()){
            throw new ResourceNotFoundException("Invalid Id");
        }
        return optional.get();
    }
}

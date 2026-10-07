package com.librarymanagement.service;

import com.librarymanagement.dto.BookRespDto;
import com.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.mapper.BookMapper;
import com.librarymanagement.model.Book;
import com.librarymanagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookRespDto> findAll() {
        List<Book> list= bookRepository.findAll();
        return list
                .stream()
                .map(BookMapper::convertEntityToDto)
                .toList();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book findById(Long id) {
        Optional<Book> optional =bookRepository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceNotFoundException("Invalid Id");
        }
        Book book=optional.get();
        return book;
    }
}

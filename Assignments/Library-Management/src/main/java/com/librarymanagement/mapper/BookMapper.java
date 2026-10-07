package com.librarymanagement.mapper;

import com.librarymanagement.dto.BookRespDto;
import com.librarymanagement.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public static BookRespDto convertEntityToDto(Book book){
        return new BookRespDto(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getStatus(),
                book.getAuthor()==null?
                null:book.getAuthor().getName(),
                book.getPublishedYear()
        );
    }
}

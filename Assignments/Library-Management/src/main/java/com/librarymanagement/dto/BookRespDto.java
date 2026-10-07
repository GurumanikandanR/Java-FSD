package com.librarymanagement.dto;

import com.librarymanagement.enums.Genre;
import com.librarymanagement.enums.Status;
import com.librarymanagement.model.Author;

public record BookRespDto(
    Long id,
    String title,
    Genre genre,
    Status status,
    String authorName,
    int publishedYear
){}
//id Long (auto-generated)
//title String
//genre enum Genre → FICTION, NON_FICTION, SCIENCE, HISTORY, BIOGRAPHY
//status enum BookStatus → AVAILABLE, BORROWED, LOST
//author @ManyToOne → Author (a book has exactly one author)
//borrowedBy @ManyToOne → Member (nullable — null when not currently borrowed)
//publishedYear int

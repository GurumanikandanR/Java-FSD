package com.librarymanagement.model;

import com.librarymanagement.enums.Genre;
import com.librarymanagement.enums.Status;
import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private Genre genre;
    private Status status;
    private int publishedYear;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Author author;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", status=" + status +
                ", publishedYear=" + publishedYear +
                ", author=" + author +
                ", member=" + member +
                '}';
    }

    @ManyToOne
    @JoinColumn(nullable = true)
    private Member member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(Long id, String title, Genre genre, Status status, int publishedYear, Author author, Member member) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.status = status;
        this.publishedYear = publishedYear;
        this.author = author;
        this.member = member;
    }
}
//id Long (auto-generated)
//title String
//genre enum Genre → FICTION, NON_FICTION, SCIENCE, HISTORY, BIOGRAPHY
//status enum BookStatus → AVAILABLE, BORROWED, LOST
//author @ManyToOne → Author (a book has exactly one author)
//borrowedBy @ManyToOne → Member (nullable — null when not currently borrowed)
//publishedYear int
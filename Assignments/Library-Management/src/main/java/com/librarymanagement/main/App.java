package com.librarymanagement.main;

import com.librarymanagement.config.AppConfig;
import com.librarymanagement.dto.BookRespDto;
import com.librarymanagement.enums.Genre;
import com.librarymanagement.enums.Status;
import com.librarymanagement.model.Author;
import com.librarymanagement.model.Book;
import com.librarymanagement.model.Member;
import com.librarymanagement.service.AuthorService;
import com.librarymanagement.service.BookService;
import com.librarymanagement.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);

        BookService bookService=context.getBean(BookService.class);
        AuthorService authorService=context.getBean(AuthorService.class);
        MemberService memberService=context.getBean(MemberService.class);

        Scanner sc=new Scanner(System.in);

        while(true){
            System.out.println("------------------Library Management System--------------");
            System.out.println("1.Insert Book");
            System.out.println("2.Find Book by Id");
            System.out.println("3.Get all Books");
            System.out.println("0.Exit");
            System.out.println("----------------------------------------------------------");
            int option=sc.nextInt();
            sc.nextLine();

            if(option==0) {
                System.out.println("Exiting...");
                break;
            }
            switch(option){
                case 1->{
                    //Insertion
                    Book book=new Book();

                    System.out.println("Enter the title");
                    book.setTitle(sc.nextLine());

                    System.out.println("Enter the genre");
                    book.setGenre(Genre.valueOf(sc.nextLine().toUpperCase()));

                    System.out.println("Enter the status");
                    book.setStatus(Status.valueOf(sc.nextLine().toUpperCase()));

                    System.out.println("Enter the author ID");
                    Long authorId = sc.nextLong();
                    sc.nextLine();

                    Author author=authorService.getById(authorId);
                    book.setAuthor(author);

                    System.out.println("Enter the member ID");
                    Long memberId = sc.nextLong();
                    sc.nextLine();

                    if (memberId != 0) {
                        Member member=memberService.getById(memberId);
                        book.setMember(member);
                    } else {
                        book.setMember(null);
                    }

                    System.out.println("Enter the published year");
                    book.setPublishedYear(sc.nextInt());
                    sc.nextLine();


                    try {
                        bookService.save(book);
                        System.out.println("Book Inserted successfully");
                    } catch (Exception e) {
                        System.out.println("Insertion Failed");
                    }
                    break;
                }
                case 2->{
                    //find by id
                    System.out.println("Enter the book id to find");
                    Long id=sc.nextLong();
                    Book book=bookService.findById(id);
                    System.out.println(book);
                }
                case 3->{
                    //find all
                    List<BookRespDto> list =bookService.findAll();
                    list.forEach(System.out::println);
                    break;
                }
                default -> {
                    System.out.println("Invalid option");
                }
            }


        }
    }
}

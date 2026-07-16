package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("Spring context loaded successfully.");

        BookService bookService = (BookService) context.getBean("bookService");

        System.out.println("\n--- Testing BookService ---");
        String book = bookService.getBookById(101);
        System.out.println("Fetched: " + book);

        bookService.addBook("Clean Code by Robert Martin");

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring context closed.");
    }
}

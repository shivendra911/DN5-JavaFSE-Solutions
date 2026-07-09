package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("context loaded");

        BookService svc = (BookService) context.getBean("bookService");

        System.out.println("testing service");
        String book = svc.getBookById(101);
        System.out.println("got: " + book);

        svc.addBook("Clean Code");

        ((ClassPathXmlApplicationContext) context).close();
    }
}

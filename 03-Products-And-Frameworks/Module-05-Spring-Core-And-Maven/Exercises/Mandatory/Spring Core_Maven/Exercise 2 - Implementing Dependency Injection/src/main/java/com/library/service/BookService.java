package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // setter injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("repo injected");
    }

    public String getBookById(int id) {
        System.out.println("getting book " + id);
        return bookRepository.findBookById(id);
    }

    public void addBook(String bookName) {
        System.out.println("adding book " + bookName);
        bookRepository.addBook(bookName);
    }
}

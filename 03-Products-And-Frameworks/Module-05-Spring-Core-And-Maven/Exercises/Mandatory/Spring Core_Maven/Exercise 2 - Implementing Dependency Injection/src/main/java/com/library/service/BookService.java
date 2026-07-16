package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository injected successfully.");
    }

    public String getBookById(int id) {
        System.out.println("BookService: getBookById called with id " + id);
        return bookRepository.findBookById(id);
    }

    public void addBook(String bookName) {
        System.out.println("BookService: addBook called for -> " + bookName);
        bookRepository.addBook(bookName);
    }
}

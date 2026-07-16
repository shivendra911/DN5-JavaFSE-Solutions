package com.library.repository;

public class BookRepository {

    public String findBookById(int id) {
        System.out.println("BookRepository: fetching book with id " + id);
        return "Spring in Action - Book ID: " + id;
    }

    public void addBook(String bookName) {
        System.out.println("BookRepository: adding book -> " + bookName);
    }
}

package com.library.repository;

public class BookRepository {

    public String findBookById(int id) {
        System.out.println("fetching book with id " + id);
        return "Spring in Action - ID: " + id;
    }

    public void addBook(String bookName) {
        System.out.println("adding book -> " + bookName);
    }
}

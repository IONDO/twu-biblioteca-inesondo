package com.twu.biblioteca;

import java.util.*;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public int getOrder(Book book) {
        return books.indexOf(book);
    }

    public int size() {
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(int order){
        return books.get(order - 1);
    }
    public void removeBook(Book book) {
        books.remove(book);
    }
}

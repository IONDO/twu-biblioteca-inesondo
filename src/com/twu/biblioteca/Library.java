package com.twu.biblioteca;

import java.util.List;

public class Library {
    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public int size() {
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }
}

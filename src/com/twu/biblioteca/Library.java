package com.twu.biblioteca;

import java.util.List;

public class Library {
    private List<Book> books;
    private List<Movie> movies;

    public Library(List<Book> books, List<Movie> movies) {
        this.books = books;
        this.movies = movies;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public int getBookOrder(Book book) {
        return books.indexOf(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Book getBook(int order) {
        return books.get(order - 1);
    }

    public Movie getMovie(int order) {
        return movies.get(order - 1);
    }
}

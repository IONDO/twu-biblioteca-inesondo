package com.twu.biblioteca;

import java.util.List;

public class User {
    public String userId;
    public String password;
    public Library borrowedContent;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void addBookTBorrowedContent(Book book) {
        borrowedContent.addBook(book);
    }

    public void addMovieToBorrowedContent(Movie movie) {
        borrowedContent.addMovie(movie);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedContent.getBooks();
    }
    public List<Movie> getBorrowedMovies() {
        return borrowedContent.getMovies();
    }

}

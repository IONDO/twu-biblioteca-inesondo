package com.twu.biblioteca;

public class Book {
    private String title;
    private String author;
    private int publishedYear;
    private Boolean statusCheckedOut;

    public Book(String title, String author, int publishedYear) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        statusCheckedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public Boolean getStatusCheckedOut() {
        return statusCheckedOut;
    }

    public void setStatusCheckedOut(Boolean statusCheckedOut) {
        this.statusCheckedOut = statusCheckedOut;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + publishedYear;
    }
}

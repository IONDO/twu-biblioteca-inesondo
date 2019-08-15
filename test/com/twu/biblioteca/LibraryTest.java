package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class LibraryTest {
    @Test
    public void testAddingOneBook() {
        List emptyLibrary = new ArrayList();
        Library books = new Library(emptyLibrary);
        Book java = new Book("Java for beginners", "Oracle", 2000);
        books.addBook(java);
        int numberOfBooksInLibrary = 1;
        assertThat(books.size(), equalTo(numberOfBooksInLibrary));
    }

    @Test
    public void testAddingSeveralBooks() {
        List emptyLibrary = new ArrayList();
        Library books = new Library(emptyLibrary);
        Book java = new Book("Java for beginners", "Oracle", 2000);
        Book js = new Book("JS for beginners", "ES", 2006);
        Book node = new Book("Node for beginners", "Node", 2008);
        Book react = new Book("React for beginners", "React", 2016);
        Book jUnit = new Book("jUnit for beginners", "Tester", 2005);
        books.addBook(java);
        books.addBook(js);
        books.addBook(node);
        books.addBook(react);
        books.addBook(jUnit);
        int numberOfBooksInLibrary = 5;
        assertThat(books.size(), equalTo(numberOfBooksInLibrary));
    }

    @Test
    public void testBooksOrder() {
        List emptyLibrary = new ArrayList();
        Library books = new Library(emptyLibrary);
        Book java = new Book("Java for beginners", "Oracle", 2000);
        Book js = new Book("JS for beginners", "ES", 2006);
        Book node = new Book("Node for beginners", "Node", 2008);
        Book react = new Book("React for beginners", "React", 2016);
        Book jUnit = new Book("jUnit for beginners", "Tester", 2005);
        books.addBook(java);
        books.addBook(js);
        books.addBook(node);
        books.addBook(react);
        books.addBook(jUnit);
        int expectedOrder = 4;
        assertThat(books.getOrder(jUnit), equalTo(expectedOrder));

    }
}
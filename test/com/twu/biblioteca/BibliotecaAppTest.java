package com.twu.biblioteca;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class BibliotecaAppTest {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream writer = new PrintStream(output);
    BufferedReader reader = new BufferedReader(new StringReader("1\n"));
    List emptyLibrary = new ArrayList();

    @Test
    public void testPrintingListOfBooks() {
        Library books = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        String printedInfo = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(printedInfo));

    }

    @Test
    public void testDisplayMenu() {
        Library  listOfAvailableBooks= new Library(emptyLibrary);
        Library books = new Library(emptyLibrary);
        BufferedReader reader = new BufferedReader(new StringReader(""));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testDisplayErrorOutOfRangeNumber() {
        Library books = new Library(emptyLibrary);
        BufferedReader reader = new BufferedReader(new StringReader("10\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "Please select a valid option!\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testDisplayErrorNonNumeric() {
        Library books = new Library(emptyLibrary);
        BufferedReader reader = new BufferedReader(new StringReader("one\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "Please select a valid option!\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }
    @Test
    public void testQuitApp() {
        Library books = new Library(emptyLibrary);
        BufferedReader reader = new BufferedReader(new StringReader(""));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testCheckOutBook() {
        Library books = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));
        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "3\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Thank you! Enjoy reading Node for beginners.\n"+
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testCheckOutBookWithInvalidInput() {
        Library books = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));
        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "h\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testCheckOutBookThatIsCheckedOut() {
        Library books = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));
        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "1\n" + "1\n" + "1\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Thank you! Enjoy reading Java for beginners.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Sorry, Java for beginners is not available.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testReturningBook() {
        Library books = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));
        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "2\n" + "2\n" + "2\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Thank you! Enjoy reading JavaScript for beginners.\n"+
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "Thank you for returning JavaScript for beginners.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testReturningBookThatIsNotCheckedOut() {
        Library books = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));
        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "2\n" + "2\n" + "1\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Thank you! Enjoy reading JavaScript for beginners.\n"+
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "Java for beginners is not a valid book to return.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }
}
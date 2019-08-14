package com.twu.biblioteca;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
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
                "1. List of books\n" +
                "Java for beginners | Oracle | 2000\n" +
                "JavaScript for beginners | ES | 2006\n" +
                "Node for beginners | Node | 2007\n" +
                "React for beginners | React | 2016\n" +
                "jUnit for beginners | Tester | 2005\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(printedInfo));

    }

    @Test
    public void testDisplayMenu() {
        Library books = new Library(emptyLibrary);
        BufferedReader reader = new BufferedReader(new StringReader(""));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, books);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "1. List of books\n";
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
                "1. List of books\n" +
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
                "1. List of books\n" +
                "Please select a valid option!\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }
}
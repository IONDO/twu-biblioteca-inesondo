package com.twu.biblioteca;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class BibliotecaAppTest {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream writer = new PrintStream(output);
    @Test
    public void testPrintingListOfBooks() {
        Library books = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, books);
        String printedInfo = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Java for beginners | Oracle | 2000\n" +
                "JavaScript for beginners | ES | 2006\n" +
                "Node for beginners | Node | 2007\n" +
                "React for beginners | React | 2016\n" +
                "jUnit for beginners | Tester | 2005\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(printedInfo));

    }
}
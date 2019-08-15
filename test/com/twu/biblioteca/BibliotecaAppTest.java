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
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        String printedInfo = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Please, select a valid option.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(printedInfo));

    }

    @Test
    public void testDisplayMenu() {
        List emptyListOfBooks = new ArrayList();
        List emptyListOfMovies = new ArrayList();
        Library library = new Library(emptyListOfBooks,emptyListOfMovies);
        BufferedReader reader = new BufferedReader(new StringReader(""));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testDisplayErrorOutOfRangeNumber() {
        List emptyListOfBooks = new ArrayList();
        List emptyListOfMovies = new ArrayList();
        Library library = new Library(emptyListOfBooks,emptyListOfMovies);
        BufferedReader reader = new BufferedReader(new StringReader("10\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "Please select a valid option!\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testDisplayErrorNonNumeric() {
        List emptyListOfBooks = new ArrayList();
        List emptyListOfMovies = new ArrayList();
        Library library = new Library(emptyListOfBooks,emptyListOfMovies);
        BufferedReader reader = new BufferedReader(new StringReader("one\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "Please select a valid option!\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }
    @Test
    public void testQuitApp() {
        List emptyListOfBooks = new ArrayList();
        List emptyListOfMovies = new ArrayList();
        Library library = new Library(emptyListOfBooks,emptyListOfMovies);
        BufferedReader reader = new BufferedReader(new StringReader("0\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        assertThat(biblioteca.getReadingFromConsole(), equalTo(false));
    }

    @Test
    public void testCheckOutBook() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "3\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Thank you! Enjoy reading Node for beginners.\n"+
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testCheckOutBookWithInvalidInput() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "h\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Please, select a valid option.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testCheckOutBookThatIsCheckedOut() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "1\n" + "1\n" + "1\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
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
                "3. List of movies\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Sorry, Java for beginners is not available.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testReturningBook() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "2\n" + "2\n" + "2\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
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
                "3. List of movies\n" +
                "Thank you for returning JavaScript for beginners.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testReturningBookThatIsNotCheckedOut() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader("1\n" + "2\n" + "2\n" + "1\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
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
                "3. List of movies\n" +
                "Java for beginners is not a valid book to return.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testDisplayMoviesOptionInTheMenu() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader(""));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testPrintingListOfMovies() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader("3\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "1. Titanic | James Cameron | 1998 | 7.8\n" +
                "2. Twelve Angry Men | Sidney Lumet | 1954 | 8.9\n" +
                "3. A Walk to Remember | Adam Shankman | 2002 | 7.4\n" +
                "4. The Color Purple | Steven Spielberg | 1986 | 7.8\n"+
                "5. The Lion King | Rob Minkoff and Roger Allers | 1994 | 8.5\n"+
                "Please, select a valid option.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testCheckOutMovie() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader("3\n" + "1\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "1. Titanic | James Cameron | 1998 | 7.8\n" +
                "2. Twelve Angry Men | Sidney Lumet | 1954 | 8.9\n" +
                "3. A Walk to Remember | Adam Shankman | 2002 | 7.4\n" +
                "4. The Color Purple | Steven Spielberg | 1986 | 7.8\n"+
                "5. The Lion King | Rob Minkoff and Roger Allers | 1994 | 8.5\n" +
                "Thank you! Enjoy watching Titanic.\n"+
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }

    @Test
    public void testCheckOutMovieWithInvalidInput() {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ), Arrays.asList(
                new Movie("Titanic", 1998, "James Cameron", 7.8),
                new Movie("Twelve Angry Men", 1954, "Sidney Lumet", 8.9),
                new Movie("A Walk to Remember", 2002, "Adam Shankman", 7.4),
                new Movie("The Color Purple", 1986, "Steven Spielberg", 7.8),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8.5)
        ));

        BufferedReader reader = new BufferedReader(new StringReader("3\n" + "h\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "1. Titanic | James Cameron | 1998 | 7.8\n" +
                "2. Twelve Angry Men | Sidney Lumet | 1954 | 8.9\n" +
                "3. A Walk to Remember | Adam Shankman | 2002 | 7.4\n" +
                "4. The Color Purple | Steven Spielberg | 1986 | 7.8\n"+
                "5. The Lion King | Rob Minkoff and Roger Allers | 1994 | 8.5\n" +
                "Please, select a valid option.\n" +
                "Choose an option\n" +
                "0. Quit menu\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n";
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }
}
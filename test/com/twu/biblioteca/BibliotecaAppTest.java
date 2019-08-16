package com.twu.biblioteca;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream writer = new PrintStream(output);
    private Library library = new Library(Arrays.asList(
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
    private Users users = new Users(Arrays.asList(
            new User("harry", "potter"),
            new User("golum", "keeptheringsafe"),
            new User("timon", "akunamatata"),
            new User("sebastian", "iliveunderthewater"),
            new User("lupita", "ihavebeenkissedbythesun")
    ));

    private Authentication authentication = new Authentication(users);

    @Test
    public void testLogin() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        String printedInfo = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "userId: \n" +
                "password: \n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n";
        biblioteca.run();
        assertThat(getOutput(), equalTo(printedInfo));
    }

    private String getOutput() {
        return new String(output.toByteArray());
    }

    @Test
    public void testDisplayErrorOutOfRangeNumber() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "10\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "userId: \n" +
                "password: \n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n" +
                "Please select a valid option!\n";
        biblioteca.run();
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testDisplayErrorNonNumeric() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "one\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "userId: \n" +
                "password: \n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n" +
                "Please select a valid option!\n";
        biblioteca.run();
        assertThat(getOutput(), equalTo(expectedMessage));
    }

    @Test
    public void testQuitApp() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "0\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        assertThat(authentication.isLoggedIn(), equalTo(false));
    }

    @Test
    public void testCheckOutBook() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "1\n" + "3\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "userId: \n" +
                "password: \n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Thank you! Enjoy reading Node for beginners.\n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testCheckOutBookWithInvalidInput() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "1\n" + "h\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "userId: \n" +
                "password: \n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Please, select a valid option.\n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n";
        assertThat(getOutput(), equalTo(expectedMessage));
    }

    @Test
    public void testCheckOutBookThatIsCheckedOut() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "1\n" + "1\n" + "1\n" + "1\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage = "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Thank you! Enjoy reading Java for beginners.\n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Sorry, Java for beginners is not available.\n" +
                "Choose an option\n" +
                "0. Logout\n" + ///
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testReturningBook() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "1\n" + "2\n" + "2\n" + "2\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n" +
                "userId: \n" +
                "password: \n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n" +
                "1. Java for beginners | Oracle | 2000\n" +
                "2. JavaScript for beginners | ES | 2006\n" +
                "3. Node for beginners | Node | 2007\n" +
                "4. React for beginners | React | 2016\n" +
                "5. jUnit for beginners | Tester | 2005\n" +
                "Thank you! Enjoy reading JavaScript for beginners.\n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n" +
                "Thank you for returning JavaScript for beginners.\n" +
                "Choose an option\n" +
                "0. Logout\n" +
                "1. List of books\n" +
                "2. Return a book\n" +
                "3. List of movies\n" +
                "4. Check who has checkout a book\n" +
                "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testReturningBookThatIsNotCheckedOut() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "1\n" + "2\n" + "2\n" + "1\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Java for beginners | Oracle | 2000\n" +
                        "2. JavaScript for beginners | ES | 2006\n" +
                        "3. Node for beginners | Node | 2007\n" +
                        "4. React for beginners | React | 2016\n" +
                        "5. jUnit for beginners | Tester | 2005\n" +
                        "Thank you! Enjoy reading JavaScript for beginners.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "Java for beginners is not a valid book to return.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testDisplayMoviesOptionInTheMenu() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + ""));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testPrintingListOfMovies() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "3\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Titanic | James Cameron | 1998 | 7.8\n" +
                        "2. Twelve Angry Men | Sidney Lumet | 1954 | 8.9\n" +
                        "3. A Walk to Remember | Adam Shankman | 2002 | 7.4\n" +
                        "4. The Color Purple | Steven Spielberg | 1986 | 7.8\n" +
                        "5. The Lion King | Rob Minkoff and Roger Allers | 1994 | 8.5\n" +
                        "Please, select a valid option.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testCheckOutMovie() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "3\n" + "1\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Titanic | James Cameron | 1998 | 7.8\n" +
                        "2. Twelve Angry Men | Sidney Lumet | 1954 | 8.9\n" +
                        "3. A Walk to Remember | Adam Shankman | 2002 | 7.4\n" +
                        "4. The Color Purple | Steven Spielberg | 1986 | 7.8\n" +
                        "5. The Lion King | Rob Minkoff and Roger Allers | 1994 | 8.5\n" +
                        "Thank you! Enjoy watching Titanic.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testCheckOutMovieWithInvalidInput() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "3\n" + "h\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Titanic | James Cameron | 1998 | 7.8\n" +
                        "2. Twelve Angry Men | Sidney Lumet | 1954 | 8.9\n" +
                        "3. A Walk to Remember | Adam Shankman | 2002 | 7.4\n" +
                        "4. The Color Purple | Steven Spielberg | 1986 | 7.8\n" +
                        "5. The Lion King | Rob Minkoff and Roger Allers | 1994 | 8.5\n" +
                        "Please, select a valid option.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }
    @Test
    public void testCheckWhoHasTheBookCheckedOut() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "1\n" + "2\n" + "4\n" + "2\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Java for beginners | Oracle | 2000\n" +
                        "2. JavaScript for beginners | ES | 2006\n" +
                        "3. Node for beginners | Node | 2007\n" +
                        "4. React for beginners | React | 2016\n" +
                        "5. jUnit for beginners | Tester | 2005\n" +
                        "Thank you! Enjoy reading JavaScript for beginners.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Java for beginners | Oracle | 2000\n" +
                        "2. JavaScript for beginners | ES | 2006\n" +
                        "3. Node for beginners | Node | 2007\n" +
                        "4. React for beginners | React | 2016\n" +
                        "5. jUnit for beginners | Tester | 2005\n" +
                        "JavaScript for beginners was checkout by harry.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test
    public void testCheckWhoHasTheBookCheckedOutWithAnInvalidInput() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "1\n" + "2\n" + "4\n" + "6\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Java for beginners | Oracle | 2000\n" +
                        "2. JavaScript for beginners | ES | 2006\n" +
                        "3. Node for beginners | Node | 2007\n" +
                        "4. React for beginners | React | 2016\n" +
                        "5. jUnit for beginners | Tester | 2005\n" +
                        "Thank you! Enjoy reading JavaScript for beginners.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Java for beginners | Oracle | 2000\n" +
                        "2. JavaScript for beginners | ES | 2006\n" +
                        "3. Node for beginners | Node | 2007\n" +
                        "4. React for beginners | React | 2016\n" +
                        "5. jUnit for beginners | Tester | 2005\n" +
                        "Please, select a valid option.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }
    @Test
    public void testCheckWhoHasTheMovieCheckedOut() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "3\n" + "2\n" + "5\n" + "2\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Titanic | James Cameron | 1998 | 7.8\n" +
                        "2. Twelve Angry Men | Sidney Lumet | 1954 | 8.9\n" +
                        "3. A Walk to Remember | Adam Shankman | 2002 | 7.4\n" +
                        "4. The Color Purple | Steven Spielberg | 1986 | 7.8\n" +
                        "5. The Lion King | Rob Minkoff and Roger Allers | 1994 | 8.5\n" +
                        "Thank you! Enjoy watching Twelve Angry Men.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "1. Titanic | James Cameron | 1998 | 7.8\n" +
                        "2. Twelve Angry Men | Sidney Lumet | 1954 | 8.9\n" +
                        "3. A Walk to Remember | Adam Shankman | 2002 | 7.4\n" +
                        "4. The Color Purple | Steven Spielberg | 1986 | 7.8\n" +
                        "5. The Lion King | Rob Minkoff and Roger Allers | 1994 | 8.5\n" +
                        "Twelve Angry Men was checkout by harry.\n" +
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n";
        assertThat(getOutput(), endsWith(expectedMessage));
    }

    @Test @Ignore
    public void testUserCanDisplayPersonalData() {
        BufferedReader reader = new BufferedReader(new StringReader("harry\n" + "potter\n" + "3\n" + "2\n" + "5\n" + "2\n"));
        BibliotecaApp biblioteca = new BibliotecaApp(writer, reader, library, authentication);
        biblioteca.run();
        String expectedMessage =
                "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "6. User profile\n" +
                        "username: harry potter\n" +
                        "email: tesing@test.com\n" +
                        "phone: 555-555"+
                        "Choose an option\n" +
                        "0. Logout\n" +
                        "1. List of books\n" +
                        "2. Return a book\n" +
                        "3. List of movies\n" +
                        "4. Check who has checkout a book\n" +
                        "5. Check who has checkout a movie\n" +
                        "6. User profile";
        assertThat(getOutput(), endsWith(expectedMessage));
    }




}
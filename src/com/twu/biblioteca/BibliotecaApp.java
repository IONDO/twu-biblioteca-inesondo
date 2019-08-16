package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Optional;

public class BibliotecaApp {
    private final PrintStream writer;
    private final BufferedReader reader;
    private final Library library;
    private Boolean readingFromConsole;


    public BibliotecaApp(PrintStream writer, BufferedReader reader, Library library) {
        this.writer = writer;
        this.reader = reader;
        this.library = library;
        readingFromConsole = true;
    }

    public void run() {
        printGreeting();
        printMenu();
        if (readingFromConsole == true) {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    if (line == null) {
                        return;
                    }
                    int chosenOption = parseOption(line);
                    switch (chosenOption) {
                        case 0:
                            readingFromConsole = false;
                            reader.close();
                            break;
                        case 1:
                            printBooksInLibrary();
                            checkOutBook();
                            break;
                        case 2:
                            returnBook();
                            break;
                        case 3:
                            printMoviesInLibrary();
                            checkOutMovie();
                            break;
                        default:
                            writer.println("Please select a valid option!");
                    }
                }
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    private void printGreeting() {
        writer.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
    }

    private void printBooksInLibrary() {
        int orderedList = 1;
        for (Book book : library.getBooks()) {
            writer.println(orderedList + ". " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getPublishedYear());
            orderedList++;
        }
    }

    private void printMoviesInLibrary() {
        int orderedList = 1;
        for (Movie movie : library.getMovies()) {
            writer.println(orderedList + ". " + movie.getName() + " | "
                    + movie.getDirector() + " | " + movie.getYear() + " | " + movie.getRating());
            orderedList++;
        }
    }

    private int parseOption(String line) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private void printMenu() {
        writer.println("Choose an option");
        writer.println("0. Quit menu");
        writer.println("1. List of books");
        writer.println("2. Return a book");
        writer.println("3. List of movies");
    }

    public int readOption() {
        Optional<String> optionalString = Optional.empty();
        int chosenBookOrder = 0;
        try {
            String line = reader.readLine();
            optionalString = Optional.ofNullable(line);
            if (optionalString.isPresent()) {
                chosenBookOrder = parseOption(line);
            }
        } catch (IOException ignored) {
        }
        return chosenBookOrder;
    }

    public void checkOutBook() {
        int chosenBookOrder = readOption();
        if (chosenBookOrder < 0) {
            writer.println("Please, select a valid option.");
            printMenu();
            return;
        }
        if (chosenBookOrder <= library.getBooks().size() && chosenBookOrder > 0) {
            Book chosenBook = library.getBook(chosenBookOrder);
            if (chosenBook.getStatusCheckedOut()) {
                writer.println("Sorry, " + chosenBook.getTitle() + " is not available.");
                printMenu();
            } else {
                chosenBook.setStatusCheckedOut(true);
                writer.println("Thank you! Enjoy reading " + chosenBook.getTitle() + ".");
                printMenu();
            }
        } else {
            writer.println("Please, select a valid option.");
            printMenu();
        }
    }

    public void checkOutMovie() {
        int chosenMovieOrder = readOption();
        if (chosenMovieOrder < 0) {
            writer.println("Please, select a valid option.");
            printMenu();
            return;
        }
        if (chosenMovieOrder <= library.getMovies().size() && chosenMovieOrder > 0) {
            Movie chosenMovie = library.getMovie(chosenMovieOrder);
            if (chosenMovie.getStatusCheckedOut()) {
                writer.println("Sorry, " + chosenMovie.getName() + " is not available.");
                printMenu();
            } else {
                chosenMovie.setStatusCheckedOut(true);
                writer.println("Thank you! Enjoy watching " + chosenMovie.getName() + ".");
                printMenu();
            }
        } else {
            writer.println("Please, select a valid option.");
            printMenu();
        }

    }

    public Boolean getReadingFromConsole() {
        return readingFromConsole;
    }

    public void returnBook() {
        int chosenBookOrder = readOption();
        if (chosenBookOrder < 0) {
            return;
        }
        if (chosenBookOrder <= library.getBooks().size()) {
            Book chosenBook = library.getBook(chosenBookOrder);
            if (chosenBook.getStatusCheckedOut()) {
                chosenBook.setStatusCheckedOut(false);
                writer.println("Thank you for returning " + chosenBook.getTitle() + ".");
                printMenu();
            } else {
                writer.println(chosenBook.getTitle() + " is not a valid book to return.");
                printMenu();
            }
        } else {
            writer.println("This is not a valid book to return.");
            printMenu();
        }


    }

    public static void main(String[] args) {
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

        BibliotecaApp biblioteca = new BibliotecaApp(System.out, new BufferedReader(new InputStreamReader(System.in)), library);
        biblioteca.run();
    }
}

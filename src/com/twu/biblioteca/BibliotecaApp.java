package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Arrays;

public class BibliotecaApp {
    private final PrintStream writer;
    private final Library library;

    public BibliotecaApp(PrintStream writer, Library library) {
        this.writer = writer;
        this.library = library;
    }

    public void run(){
        printGreeting();
        printLibraryBooks();
    }

    private void printGreeting() {
        writer.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
    }
    private void printLibraryBooks() {
        for ( Book book: library.getBooks()) {
            writer.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getPublishedYear());
        }
    }

    public static void main(String[] args) {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));

        BibliotecaApp biblioteca = new BibliotecaApp(System.out, library);
        biblioteca.run();
    }
}

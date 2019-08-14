package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class BibliotecaApp {
    private final PrintStream writer;
    private final BufferedReader reader;
    private final Library library;

    public BibliotecaApp(PrintStream writer, BufferedReader reader, Library library) {
        this.writer = writer;
        this.reader = reader;
        this.library = library;
    }

    public void run(){
        printGreeting();
        printMenu();
        try {
            String line = reader.readLine();
            if(line == null) {
                return;
            }
            int optionChosen = parseOption(line);
            switch (optionChosen) {
                case 1:
                    printLibraryBooks();
                    break;
                default:
                    writer.println("Please select a valid option!");
            }
        } catch (IOException e) {
        }
    }

    private void printGreeting() {
        writer.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
    }
    private void printLibraryBooks() {
        for ( Book book: library.getBooks()) {
            writer.println(book.getTitle() + " | " + book.getAuthor() + " | " + book.getPublishedYear());
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
        writer.println("1. List of books");
    }

    public static void main(String[] args) {
        Library library = new Library(Arrays.asList(
                new Book("Java for beginners", "Oracle", 2000),
                new Book("JavaScript for beginners", "ES", 2006),
                new Book("Node for beginners", "Node", 2007),
                new Book("React for beginners", "React", 2016),
                new Book("jUnit for beginners", "Tester", 2005)
        ));

        BibliotecaApp biblioteca = new BibliotecaApp(System.out, new BufferedReader(new InputStreamReader(System.in)), library);
        biblioteca.run();
    }
}

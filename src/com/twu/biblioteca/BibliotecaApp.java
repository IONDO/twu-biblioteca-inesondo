package com.twu.biblioteca;

import java.io.PrintStream;

public class BibliotecaApp {
    private final PrintStream writer;


    public BibliotecaApp(PrintStream writer) {
        this.writer = writer;
    }

    public void run(){
        printGreeting();
    }

    private void printGreeting() {
        writer.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
    }

    public static void main(String[] args) {
        BibliotecaApp biblioteca = new BibliotecaApp(System.out);
        biblioteca.run();
    }
}

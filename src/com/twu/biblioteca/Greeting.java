package com.twu.biblioteca;

import java.io.PrintStream;

public class Greeting {
    private final String message;
    private final PrintStream writer;

    public Greeting(String message, PrintStream writer) {
        this.message = message;
        this.writer = writer;
    }

    public void printWelcomeMessage() {
        writer.println(message);
    }

}

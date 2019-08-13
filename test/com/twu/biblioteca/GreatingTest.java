package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class GreatingTest {
    @Test
    public void checkInWelcomeMessage() {
        Greating message = new Greating("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
        String text = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!";
        message.printWelcomeMessage();
        assertEquals(message.toString(), text);
    }
}
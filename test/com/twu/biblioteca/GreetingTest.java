package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class GreetingTest {
    @Test
    public void testWelcomeMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(output);
        Greeting greeting = new Greeting("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!", writer);
        String expectedMessage = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n";
        greeting.printWelcomeMessage();
        assertThat(new String(output.toByteArray()), equalTo(expectedMessage));
    }
}
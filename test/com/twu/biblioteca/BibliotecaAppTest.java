package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class BibliotecaAppTest {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream writer = new PrintStream(output);
    @Test
    public void testGreeting() {
        BibliotecaApp biblioteca = new BibliotecaApp(writer);
        String expectedGreeting = "Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n";
        biblioteca.run();
        assertThat(new String(output.toByteArray()), equalTo(expectedGreeting));
    }
}
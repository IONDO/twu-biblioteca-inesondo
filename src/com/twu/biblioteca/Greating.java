package com.twu.biblioteca;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class Greating {
    private String message;

    public Greating(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void printWelcomeMessage() {
        System.out.println(message);
    }

    @Override
    public String toString() {
        return message ;
    }


}

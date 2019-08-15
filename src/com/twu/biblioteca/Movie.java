package com.twu.biblioteca;

public class Movie {
    private String name;
    private int year;
    private String director;
    private double rating;
    private Boolean statusCheckedOut;

    public Movie(String name, int year, String director, double rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        statusCheckedOut = false;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setStatusCheckedOut(Boolean statusCheckedOut) {
        this.statusCheckedOut = statusCheckedOut;
    }

    public String getDirector() {
        return director;
    }

    public Boolean getStatusCheckedOut() {
        return statusCheckedOut;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

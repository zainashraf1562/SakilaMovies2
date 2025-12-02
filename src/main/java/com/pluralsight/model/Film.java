package com.pluralsight.model;

public class Film {
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private double length;

    public Film(int filmId, String title, String description, int releaseYear, double length) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.length = length;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return  "Film ID: " + filmId + '\n'+
                "Title: " + title + '\n' +
                "Description: " + description + '\n' +
                "Release Year: " + releaseYear + '\n'+
                "Length: " + length + '\n'+
                "==============================";
    }
}

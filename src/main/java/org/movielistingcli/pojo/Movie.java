package org.movielistingcli.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private String title;
    private String cast;
    private String category;
    private String releaseDate;
    private double budget;

    @Override
    public String toString() {
        return "Title: " + title + ",Cast: " + cast + ",Category: " + category +
                ", Release Date: " + releaseDate + ",Budget: " + budget;
    }
}
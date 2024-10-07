package org.movielistingcli.pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String email;
    private final List<Movie> favoriteMovies;

    public User(String email) {
        this.email = email;
        this.favoriteMovies = new ArrayList<>();
    }

    public void addFavoriteMovie(Movie movie) {
        favoriteMovies.add(movie);
    }

    public void removeFavoriteMovie(String title) {
        favoriteMovies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
        System.out.println("Movie removed from favorites.");
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public String getEmail() {
        return email;
    }
}
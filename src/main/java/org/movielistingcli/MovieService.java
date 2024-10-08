package org.movielistingcli;

import org.movielistingcli.pojo.Movie;
import org.movielistingcli.pojo.User;

import java.util.*;

public class MovieService {
    public  void initializeMovies(List<Movie> movies) {
        movies.add(new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", "2010-07-16", 160000000));
        movies.add(new Movie("The Matrix", "Keanu Reeves", "Action", "1999-03-31", 63000000));
        movies.add(new Movie("Titanic", "Leonardo DiCaprio", "Romance", "1997-12-19", 200000000));
        movies.add(new Movie("The Dark Knight", "Christian Bale", "Action", "2008-07-18", 185000000));
        movies.add(new Movie("Avengers: Endgame", "Robert Downey Jr.", "Action", "2019-04-26", 356000000));
        movies.add(new Movie("Interstellar", "Matthew McConaughey", "Sci-Fi", "2014-11-07", 165000000));
        movies.add(new Movie("Gladiator", "Russell Crowe", "Action", "2000-05-05", 103000000));
        movies.add(new Movie("The Godfather", "Marlon Brando", "Crime", "1972-03-24", 6000000));
        movies.add(new Movie("Pulp Fiction", "John Travolta", "Crime", "1994-10-14", 8000000));
        movies.add(new Movie("Forrest Gump", "Tom Hanks", "Drama", "1994-07-06", 55000000));
        movies.add(new Movie("The Shawshank Redemption", "Tim Robbins", "Drama", "1994-09-23", 25000000));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "Elijah Wood", "Fantasy", "2001-12-19", 93000000));
        movies.add(new Movie("Star Wars: Episode IV - A New Hope", "Mark Hamill", "Sci-Fi", "1977-05-25", 11000000));
        movies.add(new Movie("Jurassic Park", "Sam Neill", "Adventure", "1993-06-11", 63000000));
    }

    public  int getUserChoice(Scanner scanner) {
        int choice = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException ex) {
                System.out.println("Please provide a valid input (number between 0-9).");
                scanner.nextLine();
            }
        }
        return choice;
    }

    public void registerUser(Scanner scanner, Map<String, User> users) {
        System.out.print("Enter your email to register: ");
        String email = scanner.nextLine();
        if (!email.contains("@") || !email.endsWith(".com")) {
            System.out.println("Please enter a valid email address.");
        } else if (users.containsKey(email)) {
            System.out.println("User already registered.");
        } else {
            users.put(email, new User(email));
            System.out.println("User registered successfully.");
        }
    }

    public User loginUser(Scanner scanner, Map<String, User> users) {
        System.out.print("Enter your email to login: ");
        String email = scanner.nextLine();
        if (users.containsKey(email)) {
            System.out.println("Login successful.");
            return users.get(email);
        } else {
            System.out.println("User not found. Please register first.");
            return null;
        }
    }

    public void searchMovies(Scanner scanner, List<Movie> movies) {
        System.out.print("Enter search term (title, cast, category): ");
        String query = scanner.nextLine().toLowerCase();
        movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query) ||
                        movie.getCast().toLowerCase().contains(query) ||
                        movie.getCategory().toLowerCase().contains(query))
                .sorted(Comparator.comparing(Movie::getTitle))
                .forEach(System.out::println);
    }

    public void viewMovieDetails(Scanner scanner, List<Movie> movies) {
        System.out.print("Enter movie title to view details: ");
        String title = scanner.nextLine();
        movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Movie not found."));
    }

    public void addMovieToFavorites(Scanner scanner, User currentUser, List<Movie> movies) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.print("Enter movie title to add to favorites: ");
        String title = scanner.nextLine();
        movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .ifPresentOrElse(movie -> {
                    currentUser.addFavoriteMovie(movie);
                    System.out.println("Movie added to favorites.");
                }, () -> System.out.println("Movie not found."));
    }

    public void removeMovieFromFavorites(Scanner scanner, User currentUser) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.print("Enter movie title to remove from favorites: ");
        String title = scanner.nextLine();
        currentUser.removeFavoriteMovie(title);
    }

    public void viewFavoriteMovies(User currentUser) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        currentUser.getFavoriteMovies().forEach(System.out::println);
    }

    public void searchFavoriteMovies(Scanner scanner, User currentUser) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.print("Enter search term for favorite movies: ");
        String query = scanner.nextLine().toLowerCase();
        currentUser.getFavoriteMovies().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query) ||
                        movie.getCast().toLowerCase().contains(query) ||
                        movie.getCategory().toLowerCase().contains(query))
                .sorted(Comparator.comparing(Movie::getTitle))
                .forEach(System.out::println);
    }

    public User logout() {
        System.out.println("Logged out successfully.");
        return null;
    }
}

package org.movielistingcli;


import org.movielistingcli.pojo.Movie;
import org.movielistingcli.pojo.User;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Movie> movies = new ArrayList<>();
    static Map<String, User> users = new HashMap<>();
    static User currentUser = null;

    public static void main(String[] args) {
        MovieService.initializeMovies(movies);
        showMainMenu();
    }

    private static void showMainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Movie Listing Application ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Search Movies");
            System.out.println("4. View Movie Details");
            System.out.println("5. Add Movie to Favorites");
            System.out.println("6. Remove Movie from Favorites");
            System.out.println("7. View Favorite Movies");
            System.out.println("8. Search Favorite Movies");
            System.out.println("9. Logout");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = MovieService.getUserChoice(scanner);

            switch (choice) {
                case 1 -> MovieService.registerUser(scanner, users);
                case 2 -> currentUser = MovieService.loginUser(scanner, users);
                case 3 -> MovieService.searchMovies(scanner, movies);
                case 4 -> MovieService.viewMovieDetails(scanner, movies);
                case 5 -> MovieService.addMovieToFavorites(scanner, currentUser, movies);
                case 6 -> MovieService.removeMovieFromFavorites(scanner, currentUser);
                case 7 -> MovieService.viewFavoriteMovies(currentUser);
                case 8 -> MovieService.searchFavoriteMovies(scanner, currentUser);
                case 9 -> currentUser = MovieService.logout();
                case 0 -> {
                    exit = true;
                    System.out.println("Thank you for using the Movie Listing Application. Goodbye!");
                    scanner.close();
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
package org.movielistingcli;


import org.movielistingcli.pojo.Movie;
import org.movielistingcli.pojo.User;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final  List<Movie> movies = new ArrayList<>();
    private static final  Map<String, User> users = new HashMap<>();
    private static User currentUser = null;
    private static MovieService movieService;

    public static void main(String[] args) {
        movieService=new MovieService();
        movieService.initializeMovies(movies);
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

            int choice = movieService.getUserChoice(scanner);

            switch (choice) {
                case 1 -> movieService.registerUser(scanner, users);
                case 2 -> currentUser = movieService.loginUser(scanner, users);
                case 3 -> movieService.searchMovies(scanner, movies);
                case 4 -> movieService.viewMovieDetails(scanner, movies);
                case 5 -> movieService.addMovieToFavorites(scanner, currentUser, movies);
                case 6 -> movieService.removeMovieFromFavorites(scanner, currentUser);
                case 7 -> movieService.viewFavoriteMovies(currentUser);
                case 8 -> movieService.searchFavoriteMovies(scanner, currentUser);
                case 9 -> currentUser = movieService.logout();
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
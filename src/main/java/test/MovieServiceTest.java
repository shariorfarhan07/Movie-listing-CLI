package test;

import org.movielistingcli.MovieService;
import org.movielistingcli.pojo.Movie;
import org.movielistingcli.pojo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {

    private List<Movie> movies;
    private Map<String, User> users;
    private User currentUser;
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movies = new ArrayList<>();
        users = new HashMap<>();
        movieService = new MovieService();
        movieService.initializeMovies(movies);
    }

    @Test
    public void testInitializeMovies() {
        assertEquals(14, movies.size(), "Movies list should contain 14 movies after initialization.");
    }

    @Test
    public void testGetUserChoice_ValidInput() {
        Scanner scanner = new Scanner("5\n");
        int choice = movieService.getUserChoice(scanner);
        assertEquals(5, choice, "User choice should be 5.");
    }

    @Test
    public void testGetUserChoice_InvalidInput() {
        Scanner scanner = new Scanner("abc\n5\n");
        int choice = movieService.getUserChoice(scanner);
        assertEquals(5, choice, "User choice should be 5 after providing an invalid input.");
    }

    @Test
    public void testRegisterUser_ValidEmail() {
        Scanner scanner = new Scanner("test@example.com\n");
        movieService.registerUser(scanner, users);
        assertTrue(users.containsKey("test@example.com"), "User should be registered successfully with a valid email.");
    }

    @Test
    public void testRegisterUser_InvalidEmail() {
        Scanner scanner = new Scanner("invalidemail\n");
        movieService.registerUser(scanner, users);
        assertFalse(users.containsKey("invalidemail"), "User should not be registered with an invalid email.");
    }

    @Test
    public void testRegisterUser_AlreadyRegistered() {
        users.put("test@example.com", new User("test@example.com"));
        Scanner scanner = new Scanner("test@example.com\n");
        movieService.registerUser(scanner, users);
        assertEquals(1, users.size(), "User should not be registered again if already registered.");
    }

    @Test
    public void testLoginUser_SuccessfulLogin() {
        users.put("test@example.com", new User("test@example.com"));
        Scanner scanner = new Scanner("test@example.com\n");
        User user = movieService.loginUser(scanner, users);
        assertNotNull(user, "User should be able to login with a registered email.");
    }

    @Test
    public void testLoginUser_UserNotFound() {
        Scanner scanner = new Scanner("notregistered@example.com\n");
        User user = movieService.loginUser(scanner, users);
        assertNull(user, "User should not be able to login if not registered.");
    }

    @Test
    public void testSearchMovies_TitleSearch() {
        Scanner scanner = new Scanner("inception\n");
        movieService.searchMovies(scanner, movies);
        long count = movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase("Inception")).count();
        assertEquals(1, count, "Should find one movie with title 'Inception'.");
    }

    @Test
    public void testAddMovieToFavorites_MovieExists() {
        currentUser = new User("test@example.com");
        Scanner scanner = new Scanner("Inception\n");
        movieService.addMovieToFavorites(scanner, currentUser, movies);
        assertTrue(currentUser.getFavoriteMovies().stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase("Inception")), "Movie 'Inception' should be added to favorites.");
    }

    @Test
    public void testAddMovieToFavorites_MovieNotFound() {
        currentUser = new User("test@example.com");
        Scanner scanner = new Scanner("Nonexistent Movie\n");
        movieService.addMovieToFavorites(scanner, currentUser, movies);
        assertFalse(currentUser.getFavoriteMovies().stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase("Nonexistent Movie")), "Nonexistent movie should not be added to favorites.");
    }

    @Test
    public void testRemoveMovieFromFavorites_MovieExists() {
        currentUser = new User("test@example.com");
        Movie inception = movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase("Inception")).findFirst().orElse(null);
        currentUser.addFavoriteMovie(inception);
        Scanner scanner = new Scanner("Inception\n");
        movieService.removeMovieFromFavorites(scanner, currentUser);
        assertFalse(currentUser.getFavoriteMovies().contains(inception), "Movie 'Inception' should be removed from favorites.");
    }

    @Test
    public void testViewFavoriteMovies_LoggedIn() {
        currentUser = new User("test@example.com");
        Movie inception = movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase("Inception")).findFirst().orElse(null);
        currentUser.addFavoriteMovie(inception);
        movieService.viewFavoriteMovies(currentUser);
        assertEquals(1, currentUser.getFavoriteMovies().size(), "User should see one favorite movie.");
    }

    @Test
    public void testViewFavoriteMovies_NotLoggedIn() {
        currentUser = null;
        movieService.viewFavoriteMovies(currentUser);
    }

    @Test
    public void testLogout() {
        currentUser = new User("test@example.com");
        currentUser = movieService.logout();
        assertNull(currentUser, "User should be logged out and currentUser should be null.");
    }
}
# Movie Listing Application Functional Requirements

## 1. **User Registration**

### Description:
- Users should be able to register using only their email address. No authentication system is required, meaning no password or login functionality needs to be implemented.

### Functional Requirements:
- **Register user**: The system should accept and store a unique email address for each user.
- **No authentication**: No user verification or login/logout functionality needed.

## 2. **Search Movies**

### Description:
- Users should be able to search for movies based on title, cast, or category. The search results should be displayed in ascending order of the movie title.

### Functional Requirements:
- **Search by title**: The system should allow users to search for movies using a partial or complete movie title.
- **Search by cast**: The system should allow users to search by cast members.
- **Search by category**: Users should be able to search movies by category (e.g., Action, Comedy, etc.).
- **Results sorting**: Search results should be returned in ascending alphabetical order based on the movie title.

## 3. **View Movie Details**

### Description:
- Users should be able to view detailed information for each movie listed in the app.

### Functional Requirements:
- **Movie details**: Each movie should display the following details:
    - Title
    - Cast (actors, actresses, etc.)
    - Category (genre)
    - Release date
    - Budget

## 4. **Favorites Management**

### Description:
- Users should be able to add and remove movies from their list of favorites.

### Functional Requirements:
- **Add to favorites**: Users can select a movie and add it to their personal favorites list.
- **Remove from favorites**: Users can remove a movie from their favorites list.

## 5. **View Personal Details and Favorites**

### Description:
- Users should be able to view their registered email address and the list of movies they’ve added to their favorites.

### Functional Requirements:
- **View personal details**: Users should be able to see the email address they registered with.
- **View favorite movies**: Users should be able to see a list of all movies they have added to their favorites.

## 6. **Search within Favorites**

### Description:
- Users should be able to search for movies within their list of favorites.

### Functional Requirements:
- **Search favorite movies**: Users should be able to search their favorite movies by title, cast, or category, similar to the global search but limited to their favorites list.

## Constraints
- **No Database Required**: The system should function without the use of a traditional database. Data could be stored in memory or other simple structures as needed.

## General Instructions
- **TDD Approach**: The application must follow the Test-Driven Development (TDD) methodology, where tests are written before the actual code implementation.

## Deliverables
- A Java console application fulfilling the above requirements.
- Code should be committed to a GitHub repository with a well-maintained commit history to showcase TDD practices.

### Points to be noted
- No need to use database 
- TDD must 
- Search all movie in the app, otherwise my thought would be to use an opensource movie api 

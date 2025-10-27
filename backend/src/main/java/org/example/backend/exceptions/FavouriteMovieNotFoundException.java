package org.example.backend.exceptions;

public class FavouriteMovieNotFoundException extends RuntimeException {
    public FavouriteMovieNotFoundException(String message) {
        super(message);
    }
}

package org.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HallNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleHallNotFoundException(HallNotFoundException e){
        return e.getMessage();
    }


    @ExceptionHandler(FavouriteMovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFavMovieNotFoundException(FavouriteMovieNotFoundException e){
        return e.getMessage();
    }
}

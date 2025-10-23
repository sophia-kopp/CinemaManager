package org.example.backend.controller;

import org.example.backend.model.movie.FavouriteMovie;
import org.example.backend.service.FavouriteMovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favouriteMovies")
public class FavouriteMovieController {
    private final FavouriteMovieService service;

    public FavouriteMovieController(FavouriteMovieService service) {
        this.service = service;
    }

    @PostMapping
    public FavouriteMovie addNewFavouriteMovie(@RequestBody FavouriteMovie favouriteMovie){
        return service.addNewFavouriteMovie(favouriteMovie);
    }
}

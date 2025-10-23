package org.example.backend.controller;

import org.example.backend.model.movie.FavouriteMovie;
import org.example.backend.service.FavouriteMovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favouriteMovies")
public class FavouriteMovieController {
    private final FavouriteMovieService service;

    public FavouriteMovieController(FavouriteMovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<FavouriteMovie> getAllFavouriteMovie(){
        return service.getAllFavouriteMovies();
    }

    @PostMapping
    public FavouriteMovie addNewFavouriteMovie(@RequestBody FavouriteMovie favouriteMovie){
        return service.addNewFavouriteMovie(favouriteMovie);
    }
}

package org.example.backend.service;

import org.example.backend.model.movie.FavouriteMovie;
import org.example.backend.repo.FavouriteMovieRepo;
import org.springframework.stereotype.Service;

@Service
public class FavouriteMovieService {

    private final FavouriteMovieRepo repo;

    public FavouriteMovieService(FavouriteMovieRepo repo) {
        this.repo = repo;
    }

    public FavouriteMovie addNewFavouriteMovie(FavouriteMovie favouriteMovie){
        return repo.save(favouriteMovie);
    }
}

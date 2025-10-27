package org.example.backend.service;

import org.example.backend.exceptions.FavouriteMovieNotFoundException;
import org.example.backend.model.movie.FavouriteMovie;
import org.example.backend.repo.FavouriteMovieRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteMovieService {

    private final FavouriteMovieRepo repo;

    public FavouriteMovieService(FavouriteMovieRepo repo) {
        this.repo = repo;
    }

    public FavouriteMovie addNewFavouriteMovie(FavouriteMovie favouriteMovie){
        return repo.save(favouriteMovie);
    }

    public List<FavouriteMovie> getAllFavouriteMovies() {
        return repo.findAll();
    }

    public ResponseEntity<String> deleteFavMovie(String id) {
            FavouriteMovie existingFavMovie = repo.findById(id)
                    .orElseThrow(()-> new FavouriteMovieNotFoundException("Favourite Movie not found with id: " + id));
            repo.deleteById(existingFavMovie.id());
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted.");
    }
}

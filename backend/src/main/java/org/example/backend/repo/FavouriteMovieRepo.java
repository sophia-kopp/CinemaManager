package org.example.backend.repo;

import org.example.backend.model.movie.FavouriteMovie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteMovieRepo extends MongoRepository<FavouriteMovie, String> {
}

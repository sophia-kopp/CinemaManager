package org.example.backend.repo;

import org.example.backend.model.CinemaHall;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepo extends MongoRepository<CinemaHall, String> {
}

package org.example.backend.repo;

import org.example.backend.model.Presentation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepo extends MongoRepository<Presentation, String> {
}

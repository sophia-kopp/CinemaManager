package org.example.backend.service;

import org.example.backend.exceptions.PresentationNotFoundException;
import org.example.backend.model.Presentation;
import org.example.backend.repo.PresentationRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentationService {

    private final PresentationRepo repo;

    public PresentationService(PresentationRepo repo) {
        this.repo = repo;
    }

    public List<Presentation> getAllPresentations(){
        return repo.findAll();
    }

    public Presentation addNewPresentation(Presentation presentation) {
        return repo.save(presentation);
    }

    public ResponseEntity<String> deletePresentation(String id) {
        Presentation existingPres = repo.findById(id)
                .orElseThrow(()-> new PresentationNotFoundException("Presentation not found with id: " + id));
        repo.deleteById(existingPres.id());
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted.");
    }

    public Presentation getPresentationById(String id) {
        return repo.findById(id).orElseThrow( ()->new PresentationNotFoundException("Presentation not found with id: " + id));
    }

    public Presentation updateExistingPresentation(String id, Presentation presentation) {
        Presentation existingPres = repo.findById(id)
                .orElseThrow(()-> new PresentationNotFoundException("Presentation not found with id: " + id));

        Presentation updatedPresentation = new Presentation(existingPres.id(),
                presentation.movieName(),
                presentation.startsAt(),
                presentation.endsAt(),
                presentation.cinemaHallName());
        repo.save(updatedPresentation);
        return updatedPresentation;
    }
}

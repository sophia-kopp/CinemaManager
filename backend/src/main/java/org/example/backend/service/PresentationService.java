package org.example.backend.service;

import org.example.backend.model.Presentation;
import org.example.backend.repo.PresentationRepo;
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

    public void deletePresentation(String id) {
        repo.deleteById(id);
    }
}

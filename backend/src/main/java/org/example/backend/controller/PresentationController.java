package org.example.backend.controller;

import org.example.backend.model.Presentation;
import org.example.backend.service.PresentationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presentations")
public class PresentationController {

    private final PresentationService service;

    public PresentationController(PresentationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Presentation> getAllPresentations(){
        return service.getAllPresentations();
    }

    @GetMapping("/{id}")
    public Presentation getPresentationById(@PathVariable String id){
        return service.getPresentationById(id);
    }

    @PostMapping
    public Presentation addNewPresentation(@RequestBody Presentation presentation){
        return service.addNewPresentation(presentation);
    }
    @PutMapping("/{id}")
    public Presentation updateExistingPresentation(@PathVariable String id, @RequestBody Presentation presentation){
        return service.updateExistingPresentation(id, presentation);
    }

    @DeleteMapping("/{id}")
    public void deletePresentation(@PathVariable String id){
        service.deletePresentation(id);
    }

}

package org.example.backend.controller;

import org.example.backend.model.Presentation;
import org.example.backend.service.PresentationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

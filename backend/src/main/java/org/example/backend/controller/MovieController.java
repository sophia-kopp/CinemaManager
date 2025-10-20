package org.example.backend.controller;

import org.example.backend.model.movie.Movie;
import org.example.backend.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/{page}")
    public List<Movie> getAllMoviesByPage(@PathVariable String page) {
        return service.getAllMoviesByPage(page);
    }
}

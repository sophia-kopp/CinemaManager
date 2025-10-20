package org.example.backend.service;

import org.example.backend.model.movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceTest {
    private RestClient.Builder restClient;
    private MovieService service;

    @BeforeEach
    void setup() {
        restClient = mock(RestClient.Builder.class);
        service = new MovieService(restClient);
    }

    @Test
    void getAllMovies() {

        Movie movie = new Movie(false,
                "/8YFL5QQVPy3AgrEQxNYVSgiPEbe.jpg",
               List.of(28,
                       12,
                       878),
                640146,
                "en",
                "Ant-Man and the Wasp: Quantumania",
                "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
                9272.643,
                "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
                "2023-02-15",
                "Ant-Man and the Wasp: Quantumania",
                false,
                6.5,
                1856);

        List<Movie> moviesList = new ArrayList<>();
        moviesList.add(movie);

        when(restClient.build().get().retrieve()).thenReturn((RestClient.ResponseSpec) moviesList);
        List<Movie> movies = service.getAllMovies();
        assertFalse(movies.isEmpty());
    }
}
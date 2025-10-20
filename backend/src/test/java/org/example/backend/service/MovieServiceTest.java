package org.example.backend.service;

import org.example.backend.model.movie.Movie;
import org.example.backend.repo.HallRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceTest {
//    private RestClient.Builder restClientBuilder;
//    private MovieService service;
//
//    @BeforeEach
//    void setup() {
//        restClientBuilder = mock(RestClient.Builder.class);
//        service = new MovieService(restClientBuilder);
//    }
//
//    @Test
//    void getAllMovies() {
//        when(restClientBuilder.build()).thenReturn(RestClient.create("https://api.themoviedb.org/3"));
//        List<Movie> movies = service.getAllMovies();
//        assertFalse(movies.isEmpty());
//    }
}
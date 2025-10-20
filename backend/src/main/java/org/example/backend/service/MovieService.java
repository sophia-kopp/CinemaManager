package org.example.backend.service;

import org.example.backend.model.movie.Movie;
import org.example.backend.model.movie.ResponseMovie;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MovieService {

    private final RestClient restClient;

    public MovieService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.themoviedb.org/3")
                .requestInitializer(request -> {
                    // Set dynamic headers here
                    String token =System.getenv("MOVIE_EXT_DB_TOKEN");
                    request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                    //request.getHeaders().add("Custom-Header", "SomeValue");  // Add other headers as needed
                })
                .build();
    }

    public List<Movie> getAllMoviesByPage(String page) {
        return restClient.get()
                .uri("/movie/top_rated?language=en-US&page=" + page )
                .retrieve()
                .body(ResponseMovie.class).results();
    }
}

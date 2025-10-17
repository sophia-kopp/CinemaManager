package org.example.backend.service;

import org.example.backend.model.movie.Movie;
import org.example.backend.model.movie.ResponseMovie;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class MovieService {

    private final RestClient restClient;

    public MovieService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.themoviedb.org/3")
                .requestInitializer(request -> {
                    // Set dynamic headers here
                    String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYjVlMjhhMDdhYTgzMDQ3NWRiZjcwZmUzZTYxNDk1OSIsIm5iZiI6MTc2MDY5MzkyNy45MzcsInN1YiI6IjY4ZjIwZWE3ZGIwMjUyZTBjMTVhNTJkNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TWNZzKGcTu2uSkMfJe4OE3ZwnaNYi3CyZ2fLf-kV6sE"; // Logic to fetch dynamic token
                    request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                    //request.getHeaders().add("Custom-Header", "SomeValue");  // Add other headers as needed
                })
                .build();
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = restClient.get()
                .uri("/movie/top_rated?language=en-US&page=1")
                .retrieve()
                .body(ResponseMovie.class).results();

        return movies;
    }
}

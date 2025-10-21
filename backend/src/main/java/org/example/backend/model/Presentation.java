package org.example.backend.model;

import org.example.backend.model.movie.Movie;

import java.time.LocalDateTime;

public record Presentation(String id,
                           Movie movie,
                           LocalDateTime startsAt,
                           LocalDateTime endAt,
                           CinemaHall cinemaHall) {
}

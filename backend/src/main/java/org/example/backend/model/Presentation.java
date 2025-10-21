package org.example.backend.model;

import java.time.LocalDateTime;

public record Presentation(String id,
                           String movieId,
                           LocalDateTime startsAt,
                           LocalDateTime endsAt,
                           String cinemaHallId) {
}

package org.example.backend.model;

import java.time.LocalDateTime;

public record Presentation(String id,
                           String movieName,
                           LocalDateTime startsAt,
                           LocalDateTime endsAt,
                           String cinemaHallName) {
}

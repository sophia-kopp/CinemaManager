package org.example.backend.model;

import lombok.Builder;

@Builder
public record CinemaHall(String id, String name, int rows, int seatsPerRow) {
}

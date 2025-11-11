package org.example.backend.model;

import lombok.Builder;

import java.util.List;

@Builder
public record Reservation(String id,
                          Presentation presentation,
                          int amountOfSeats,
                          List<SeatPosition> seatPositions,
                          int price) {
}

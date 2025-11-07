package org.example.backend.model;

import java.util.List;

public record Reservation(String id,
                          Presentation presentation,
                          int amountOfSeats,
                          List<SeatPosition> seatPositions,
                          double price) {
}

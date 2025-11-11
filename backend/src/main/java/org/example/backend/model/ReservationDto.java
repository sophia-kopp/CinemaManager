package org.example.backend.model;

import java.util.List;

public record ReservationDto(Presentation presentation,
                             int amountOfSeats,
                             List<SeatPosition> seatPositions,
                             double price) {
}

package org.example.backend.service;

import org.example.backend.exceptions.ReservationNotFoundException;
import org.example.backend.model.Reservation;
import org.example.backend.model.ReservationDto;
import org.example.backend.repo.ReservationRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepo repo;
    private final IdService idService;

    public ReservationService(ReservationRepo repo, IdService idService) {
        this.repo = repo;
        this.idService = idService;
    }

    public List<Reservation> getAllReservations() {
        return repo.findAll();
    }

    public Reservation addNewReservation(ReservationDto reservationDto) {
        Reservation reservation = Reservation
                .builder()
                .id(idService.generateUUid())
                .presentation(reservationDto.presentation())
                .amountOfSeats(reservationDto.amountOfSeats())
                .seatPositions(reservationDto.seatPositions())
                .price(reservationDto.price())
                .build();
        return repo.save(reservation);
    }

    public ResponseEntity<String> deleteReservationById(String id) {
        Reservation existingReservation = repo.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException("Reservation not found with id: " + id));
        repo.deleteById(existingReservation.id());
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted.");
    }
}

package org.example.backend.service;

import org.example.backend.model.Reservation;
import org.example.backend.repo.ReservationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepo repo;

    public ReservationService(ReservationRepo repo) {
        this.repo = repo;
    }

    public List<Reservation> getAllReservations() {
        return repo.findAll();
    }

    public Reservation addNewReservation(Reservation reservation) {
        return repo.save(reservation);
    }
}

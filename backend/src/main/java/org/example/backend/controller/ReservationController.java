package org.example.backend.controller;

import org.example.backend.model.Reservation;
import org.example.backend.model.ReservationDto;
import org.example.backend.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reservation> getAllReservations(){
        return service.getAllReservations();
    }

    @PostMapping
    public Reservation addNewReservation(@RequestBody ReservationDto reservationDto){
        return service.addNewReservation(reservationDto);
    }
}

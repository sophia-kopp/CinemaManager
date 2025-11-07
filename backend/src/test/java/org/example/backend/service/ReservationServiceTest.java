package org.example.backend.service;

import org.example.backend.model.Presentation;
import org.example.backend.model.Reservation;
import org.example.backend.model.SeatPosition;
import org.example.backend.repo.ReservationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationRepo mockRepo;
    private ReservationService service;

    @BeforeEach
    void setup() {
        mockRepo = mock(ReservationRepo.class);
        service = new ReservationService(mockRepo);
    }

    @Test
    void getAllReservations() {

        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Reservation reservation = new Reservation("1",
                new Presentation("test", "test", dateTime, 90, "test"),
                1,
                List.of(new SeatPosition(1,1)),
                2.5);

        List<Reservation> reservations = List.of(reservation);

        //WHEN
        when(mockRepo.findAll()).thenReturn(reservations);
        List<Reservation> actual = service.getAllReservations();

        //THEN
        verify(mockRepo).findAll();
        assertEquals(reservations, actual);
    }

    @Test
    void addNewReservation() {
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Reservation reservation = new Reservation("1",
                new Presentation("test", "test", dateTime, 90, "test"),
                1,
                List.of(new SeatPosition(1,1)),
                2.5);

        //WHEN
        when(mockRepo.save(reservation)).thenReturn(reservation);
        Reservation actual = service.addNewReservation(reservation);

        //THEN
        verify(mockRepo).save(reservation);
        assertEquals(reservation, actual);
    }
}
package org.example.backend.service;

import org.example.backend.model.*;
import org.example.backend.repo.ReservationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationRepo mockRepo;
    private ReservationService service;
    private IdService mockIdService;

    @BeforeEach
    void setup() {
        mockRepo = mock(ReservationRepo.class);
        mockIdService =mock(IdService.class);
        service = new ReservationService(mockRepo, mockIdService);
    }

    @Test
    void getAllReservations() {


        CinemaHall hall = new CinemaHall("1", "test1", 4, 4);
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Reservation reservation = new Reservation("1",
                new Presentation("test", "test", dateTime, 90, hall),
                1,
                List.of(new SeatPosition("1","1")),
                20);

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

        CinemaHall hall = new CinemaHall("1", "test1", 4, 4);
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Reservation reservation = new Reservation("1",
                new Presentation("test", "test", dateTime, 90, hall),
                1,
                List.of(new SeatPosition("1","1")),
                20);
        ReservationDto reservationDto = new ReservationDto(
                new Presentation("test", "test", dateTime, 90, hall),
                1,
                List.of(new SeatPosition("1","1")),
                20);

        //WHEN
        when(mockIdService.generateUUid()).thenReturn("1");
        when(mockRepo.save(reservation)).thenReturn(reservation);
        Reservation actual = service.addNewReservation(reservationDto);

        //THEN
        verify(mockRepo).save(reservation);
        assertEquals(reservation, actual);
    }
}
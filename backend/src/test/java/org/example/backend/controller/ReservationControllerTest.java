package org.example.backend.controller;

import org.example.backend.model.CinemaHall;
import org.example.backend.model.Presentation;
import org.example.backend.model.Reservation;
import org.example.backend.model.SeatPosition;

import org.example.backend.repo.ReservationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationRepo repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

    @Test
    @WithMockUser
    void getAllReservations() throws Exception {
        //given

        CinemaHall hall = new CinemaHall("1", "test1", 4, 4);
        String time = "05.10.2025 03:58:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Reservation reservation = new Reservation("1",
                new Presentation("1", "test", dateTime, 90, hall),
                1,
                List.of(new SeatPosition("1", "1")),
                20);
        repo.save(reservation);
        //when
        mockMvc.perform(get("/api/reservations"))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """     
                                  [
                                    {
                                        "id": "1",
                                        "presentation": {
                                            "id": "1",
                                            "movieName": "test",
                                            "startsAt": "2025-10-05T03:58:00",
                                            "duration": 90,
                                            "cinemaHall":
                                            {
                                "id": "1",
                                "name": "test1",
                                "rows": 4,
                                "seatsPerRow": 4
                                }
                                        },
                                        "amountOfSeats": 1,
                                        "seatPositions": [
                                            {
                                                "row": "1",
                                                "seatNumber": "1"
                                            }
                                        ],
                                        "price": 20
                                    }
                                ]
                                """
                ));
    }

//    @Test
//    @WithMockUser(authorities = "ADMIN")
//    void addNewReservation() throws Exception {
//        String time = "05.10.2025 03:58:00";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
//        Presentation presentation = new Presentation("1", "test", dateTime, 90, "test");
//        //when
//        mockMvc.perform(post("/api/presentations").contentType(MediaType.APPLICATION_JSON).content(
//                        """
//                                {
//                                        "id": "1",
//                                        "presentation": {
//                                            "id": "1",
//                                            "movieName": "test",
//                                            "startsAt": "2025-10-05T03:58:00",
//                                            "duration": 90,
//                                            "cinemaHallName": "test"
//                                        },
//                                        "amountOfSeats": 1,
//                                        "seatPositions": [
//                                            {
//                                                "row": 1,
//                                                "seatNumber": 1
//                                            }
//                                        ],
//                                        "price": 2.5
//                                    }
//                                """
//                ))
//                //then
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(
//                        """
//                                {
//                                                                        "id": "1",
//                                                                        "presentation": {
//                                                                            "id": "1",
//                                                                            "movieName": "test",
//                                                                            "startsAt": "2025-10-05T03:58:00",
//                                                                            "duration": 90,
//                                                                            "cinemaHallName": "test"
//                                                                        },
//                                                                        "amountOfSeats": 1,
//                                                                        "seatPositions": [
//                                                                            {
//                                                                                "row": 1,
//                                                                                "seatNumber": 1
//                                                                            }
//                                                                        ],
//                                                                        "price": 2.5
//                                                                    }
//                                """
//                        )
//                );
//    }


    @Test
    @WithMockUser(authorities = "ADMIN")
    void deleteReservation_shouldReturnEmptyList_WhenOnlyPresentationIsDeleted() throws Exception {
        //given
        CinemaHall hall = new CinemaHall("1", "test1", 4, 4);
        String time = "05.10.2025 03:58:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);

        Reservation reservation = new Reservation("1",
                new Presentation("1", "test", dateTime, 90, hall),
                1,
                List.of(new SeatPosition("1", "1")),
                20);
        repo.save(reservation);

        mockMvc.perform(delete("/api/reservations/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(get("/api/reservations"))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """     
                                [
                                ]
                                """
                ));
    }
}
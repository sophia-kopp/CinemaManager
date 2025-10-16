package org.example.backend.service;

import org.example.backend.model.CinemaHall;
import org.example.backend.model.CinemaHallDto;
import org.example.backend.repo.HallRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class HallServiceTest {
    private HallRepo mockRepo;
    private HallService hallService;

    @BeforeEach
    void setup() {
        mockRepo = mock(HallRepo.class);
        hallService = new HallService(mockRepo);
    }

    @Test
    void getAllHalls_ShouldReturnListOfHalls_WhenServiceGetAllIsCalled() {
        //GIVEN
        CinemaHall hall1 = new CinemaHall("1", "test1", 4, 4);
        CinemaHall hall2 = new CinemaHall("2", "test2", 8, 8);

        List<CinemaHall> halls = List.of(hall1, hall2);

        //WHEN
        when(mockRepo.findAll()).thenReturn(halls);
        List<CinemaHall> actual = hallService.getAllHalls();

        //THEN
        verify(mockRepo).findAll();
        assertEquals(halls, actual);
    }

    @Test
    void addNewHall_ShouldReturnHall_WhenHallIsAdded () {
        //GIVEN

        CinemaHall hall = new CinemaHall("1", "test1", 4, 4);
        CinemaHallDto hallDto = new CinemaHallDto("test2", 8, 8);

        //WHEN
        when(mockRepo.save(hall)).thenReturn(hall);
        CinemaHall actual = hallService.addNewHall(hall);

        //THEN
        verify(mockRepo).save(hall);
        assertEquals(hall, actual);
    }
}
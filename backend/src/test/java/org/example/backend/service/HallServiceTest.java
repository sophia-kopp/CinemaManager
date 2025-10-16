package org.example.backend.service;

import org.example.backend.model.CinemaHall;
import org.example.backend.model.CinemaHallDto;
import org.example.backend.repo.HallRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class HallServiceTest {
    private HallRepo mockRepo;
    private HallService hallService;
    private IdService mockIdService;

    @BeforeEach
    void setup() {
        mockRepo = mock(HallRepo.class);
        mockIdService = mock(IdService.class);
        hallService = new HallService(mockRepo, mockIdService);
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
        CinemaHallDto hallDto = new CinemaHallDto("test1", 4, 4);

        //WHEN
        when(mockRepo.save(hall)).thenReturn(hall);
        when(mockIdService.getIdForHalls()).thenReturn("1");
        CinemaHall actual = hallService.addNewHall(hallDto);

        //THEN
        verify(mockRepo).save(hall);
        verify(mockIdService).getIdForHalls();
        assertEquals(hall, actual);
    }
}
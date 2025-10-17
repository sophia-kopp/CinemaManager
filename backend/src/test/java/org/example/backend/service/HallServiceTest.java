package org.example.backend.service;

import org.example.backend.exceptions.HallNotFoundException;
import org.example.backend.model.CinemaHall;
import org.example.backend.model.CinemaHallDto;
import org.example.backend.repo.HallRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

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
    void addNewHall_ShouldReturnHall_WhenHallIsAdded() {
        //GIVEN

        CinemaHall hall = new CinemaHall("1", "test1", 4, 4);
        CinemaHallDto hallDto = new CinemaHallDto("test1", 4, 4);

        //WHEN
        when(mockRepo.save(hall)).thenReturn(hall);
        when(mockIdService.generateUUid()).thenReturn("1");
        CinemaHall actual = hallService.addNewHall(hallDto);

        //THEN
        verify(mockRepo).save(hall);
        verify(mockIdService).generateUUid();
        assertEquals(hall, actual);
    }

    @Test
    void editExistingHall_ShouldReturnUpdatedHall_WhenEdited() {
        //GIVEN
        CinemaHall hall = new CinemaHall("1", "test1", 4, 4);
        CinemaHallDto hallDto = new CinemaHallDto("test1", 4, 4);

        //WHEN
        //when(mockRepo.save(hall)).thenReturn(hall);
        when(mockRepo.findById("1")).thenReturn(Optional.of(hall));
        CinemaHall actual = hallService.editExistingHall("1", hallDto);

        //THEN
        //verify(mockRepo).save(hall);
        verify(mockRepo).findById("1");
        verify(mockRepo).save(hall);
        assertEquals(hall,actual);
    }
    @Test
    void editExistingHall_ShouldThrowException_WhenNoHallExistsWithThisId() {
        //GIVEN
        CinemaHallDto hallDto = new CinemaHallDto("test1", 4, 4);

        //WHEN
        when(mockRepo.findById("1")).thenReturn(Optional.empty());

        //THEN
        HallNotFoundException exception = assertThrows(HallNotFoundException.class, () ->
                hallService.editExistingHall("1", hallDto));
        assertEquals("Hall not found with id: 1", exception.getMessage());

        verify(mockRepo).findById("1");
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    void deleteHall_ShouldReturnStatus_WhenSuccessfullyDeleted() {
        //GIVEN
        CinemaHall hall = new CinemaHall("1", "test1", 4, 4);

        //WHEN
        //when(mockRepo.save(hall)).thenReturn(hall);
        when(mockRepo.findById("1")).thenReturn(Optional.of(hall));
        ResponseEntity<String> actual = hallService.deleteHall("1");

        //THEN
        verify(mockRepo).findById("1");
        verify(mockRepo).deleteById("1");
        assertEquals("Successfully deleted.", actual.getBody());
        assertTrue(actual.getStatusCode().is2xxSuccessful());
    }

    @Test
    void deleteHall_ShouldThrowException_WhenNoHallFound() {
        //THEN
        doNothing().when(mockRepo).deleteById("1");
        when(mockRepo.existsById("1")).thenReturn(false);

        HallNotFoundException exception = assertThrows(HallNotFoundException.class, () ->
            hallService.deleteHall("1"));

        assertEquals("Hall not found with id: 1", exception.getMessage());
    }
}
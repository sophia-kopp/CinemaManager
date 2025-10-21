package org.example.backend.service;

import org.example.backend.model.Presentation;
import org.example.backend.repo.PresentationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PresentationServiceTest {

    private PresentationRepo mockRepo;
    private PresentationService service;

    @BeforeEach
    void setup() {
        mockRepo = mock(PresentationRepo.class);
        service = new PresentationService(mockRepo);
    }

    @Test
    void getAllPresentations_ShouldReturnListOfPresentations_WhenServiceGetAllIsCalled() {
        //GIVEN
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Presentation pres1 = new Presentation("1", "1", dateTime, dateTime, "1");
        Presentation pres2 = new Presentation("2", "2", dateTime, dateTime, "2");

        List<Presentation> presentations = List.of(pres1, pres2);

        //WHEN
        when(mockRepo.findAll()).thenReturn(presentations);
        List<Presentation> actual = service.getAllPresentations();

        //THEN
        verify(mockRepo).findAll();
        assertEquals(presentations, actual);
    }

    @Test
    void addNewPresentation_ShouldReturnHall_WhenHallIsAdded() {
        //GIVEN
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Presentation pres1 = new Presentation("1", "1", dateTime, dateTime, "1");

        //WHEN
        when(mockRepo.save(pres1)).thenReturn(pres1);
        Presentation actual = service.addNewPresentation(pres1);

        //THEN
        verify(mockRepo).save(pres1);
        assertEquals(pres1, actual);
    }
}
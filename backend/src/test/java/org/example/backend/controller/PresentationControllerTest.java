package org.example.backend.controller;

import org.example.backend.model.Presentation;
import org.example.backend.repo.PresentationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class PresentationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PresentationRepo repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }


    @Test
    @WithMockUser
    void getAllPresentations_ShouldReturnListOfOnePresentation_WhenGetIsCalled() throws Exception {
        //given
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Presentation pres1 = new Presentation("1", "1", dateTime, 90, "1");
        repo.save(pres1);
        //when
        mockMvc.perform(get("/api/presentations"))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """     
                                [
                                {
                                "id": "1",
                                "movieName": "1",
                                "startsAt": "2024-08-12T11:11:11",
                                "durationInMinutes": 90,
                                "cinemaHallName": "1"
                                }
                                ]
                                """
                ));
    }

    @Test
    @WithMockUser
    void addNewPresentation_ShouldReturnOnePresentation_WhenGetId1IsCalled() throws Exception {
        //given
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Presentation pres1 = new Presentation("1", "1", dateTime, 90, "1");
        repo.save(pres1);
        //when
        mockMvc.perform(post("/api/presentations").contentType(MediaType.APPLICATION_JSON).content(
                        """
                                {
                                                                   "id": "1",
                                                                "movieName": "1",
                                                                "startsAt": "2024-08-12T11:11:11",
                                                                "durationInMinutes": 90,
                                                                "cinemaHallName": "1"}
                                """
                ))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """     
                                {
                                   "id": "1",
                                "movieName": "1",
                                "startsAt": "2024-08-12T11:11:11",
                                "durationInMinutes": 90,
                                "cinemaHallName": "1"}
                                """
                ));
    }

    @Test
    void updateExistingPresentation_ShouldReturnPresentationWithOldIdAndNewData_WhenUpdated() throws Exception {
        //given
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Presentation pres1 = new Presentation("1", "test", dateTime, 90, "test");
        repo.save(pres1);
        //when
        mockMvc.perform(put("/api/presentations/1").contentType(MediaType.APPLICATION_JSON).content(
                        """
                                {
                                                                   "id": "1",
                                                                "movieName": "testUpdate",
                                                                "startsAt": "2024-08-12T11:11:11",
                                                                "durationInMinutes": 90,
                                                                "cinemaHallName": "testUpdate"}
                                """
                ))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """     
                                {
                                   "id": "1",
                                "movieName": "testUpdate",
                                "startsAt": "2024-08-12T11:11:11",
                                "durationInMinutes": 90,
                                "cinemaHallName": "testUpdate"}
                                """
                ));
    }

    @Test
    @WithMockUser
    void deletePresentation_shouldReturnEmptyList_WhenOnlyPresentationIsDeleted() throws Exception {
        //given
        String time = "12.08.2024 11:11:11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Presentation pres1 = new Presentation("1", "1", dateTime, 90, "1");
        repo.save(pres1);

        mockMvc.perform(delete("/api/presentations/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(get("/api/presentations"))
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
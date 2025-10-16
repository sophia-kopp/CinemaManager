package org.example.backend.controller;

import org.example.backend.model.CinemaHall;
import org.example.backend.repo.HallRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class HallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HallRepo hallRepo;

    @DirtiesContext
    @Test
    void getAllHalls_ShouldReturnListOfOneHall_WhenGetIsCalled() throws Exception {
        //given
        CinemaHall hall = new CinemaHall("1", "test", 4, 4);
        hallRepo.save(hall);
        //when
        mockMvc.perform(get("/api/halls"))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """     
                                [
                                {
                                "id": "1",
                                "name": "test",
                                "rows": 4,
                                "seatsPerRow":  4}
                                ]
                                """
                ));
    }

    @Test
    void addNewHall_ShouldReturnHall_WhenNewHallIsAdded() throws Exception {
        mockMvc.perform(post("/api/halls")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                            """
                                    {
                                    "id": "1",
                                    "name": "test",
                                    "rows": 4,
                                    "seatsPerRow":  4}
                                    """
                    ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "id": "1",
                                    "name": "test",
                                    "rows": 4,
                                    "seatsPerRow":  4}
                                """
                ));
    }
}
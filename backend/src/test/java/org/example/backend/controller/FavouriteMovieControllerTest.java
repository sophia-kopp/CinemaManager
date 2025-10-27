package org.example.backend.controller;

import org.example.backend.model.movie.FavouriteMovie;
import org.example.backend.repo.FavouriteMovieRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class FavouriteMovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FavouriteMovieRepo repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

    @Test
    void getAllFavouriteMovie_ShouldReturnListOfFavMovies_WhenGetAllIsCalled() throws Exception {

        FavouriteMovie favMovie = new FavouriteMovie("1", "test1");
        repo.save(favMovie);
        //when
        mockMvc.perform(get("/api/favouriteMovies"))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                {"id":  "1",
                                "name": "test1"
                                }
                                ]
                                
                                """
                ));

    }

    @Test
    void addNewFavouriteMovie_ShouldReturnFavMovie_WhenFavMovieIsAdded() throws Exception {
        mockMvc.perform(post("/api/favouriteMovies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                        "id": "1",
                                        "name": "test1"
                                        }
                                        """
                        ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {"id":  "1",
                                "name": "test1"}
                                """
                ));
    }

    @Test
    void deleteFavMovie_ShouldReturnStatus_WhenSuccessfullyDeleted() throws Exception {
        FavouriteMovie movie = new FavouriteMovie("1", "test");
        repo.save(movie);
        mockMvc.perform(delete("/api/favouriteMovies/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(get("/api/favouriteMovies"))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                []
                                """
                ));
    }

}
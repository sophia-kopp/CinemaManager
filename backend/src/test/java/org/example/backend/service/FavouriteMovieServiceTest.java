package org.example.backend.service;

import org.example.backend.exceptions.FavouriteMovieNotFoundException;
import org.example.backend.model.movie.FavouriteMovie;
import org.example.backend.repo.FavouriteMovieRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FavouriteMovieServiceTest {

    private FavouriteMovieRepo mockRepo;
    private FavouriteMovieService service;

    @BeforeEach
    void setup() {
        mockRepo = mock(FavouriteMovieRepo.class);
        service = new FavouriteMovieService(mockRepo);
    }

    @Test
    void getAllFavouriteMovies_ShouldReturnListOfFavMovies_WhenServiceGetAllIsCalled() {
        //GIVEN
        FavouriteMovie favMovie = new FavouriteMovie("1", "test1");
        List<FavouriteMovie> favMovies = List.of(favMovie);

        //WHEN
        when(mockRepo.findAll()).thenReturn(favMovies);
        List<FavouriteMovie> actual = service.getAllFavouriteMovies();

        //THEN
        verify(mockRepo).findAll();
        assertEquals(favMovies, actual);
    }

    @Test
    void addNewFavouriteMovie_ShouldReturnFavMovie_WhenFavMovieIsAdded() {
        //GIVEN
        FavouriteMovie favMovie = new FavouriteMovie("1", "test1");

        //WHEN
        when(mockRepo.save(favMovie)).thenReturn(favMovie);
        FavouriteMovie actual = service.addNewFavouriteMovie(favMovie);

        //THEN
        verify(mockRepo).save(favMovie);
        assertEquals(favMovie, actual);
    }
    @Test
    void deleteFavMovie_ShouldThrowException_WhenNoHallFound() {
        //THEN
        doNothing().when(mockRepo).deleteById("1");
        when(mockRepo.existsById("1")).thenReturn(false);

        FavouriteMovieNotFoundException exception = assertThrows(FavouriteMovieNotFoundException.class, () ->
                service.deleteFavMovie("1"));

        assertEquals("Favourite Movie not found with id: 1", exception.getMessage());
    }
}
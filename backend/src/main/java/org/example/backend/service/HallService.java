package org.example.backend.service;

import org.example.backend.model.CinemaHall;
import org.example.backend.repo.HallRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    private final HallRepo repo;

    public HallService(HallRepo repo) {
        this.repo = repo;
    }

    public List<CinemaHall> getAllHalls(){
        return repo.findAll();
    }

    public CinemaHall addNewHall(CinemaHall hall) {
        return repo.save(hall);
    }
}

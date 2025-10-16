package org.example.backend.service;

import org.example.backend.model.CinemaHall;
import org.example.backend.model.CinemaHallDto;
import org.example.backend.repo.HallRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    private final HallRepo repo;
    private final IdService idService;

    public HallService(HallRepo repo, IdService idService) {
        this.repo = repo;
        this.idService = idService;
    }

    public List<CinemaHall> getAllHalls(){
        return repo.findAll();
    }

    public CinemaHall addNewHall(CinemaHallDto hallDto) {
        CinemaHall newHall = CinemaHall.builder()
                .id(idService.generateUUid())
                .name(hallDto.name())
                .rows(hallDto.rows())
                .seatsPerRow(hallDto.seatsPerRow())
                .build();
        return repo.save(newHall);
    }
}

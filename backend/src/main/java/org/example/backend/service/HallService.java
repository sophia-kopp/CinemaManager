package org.example.backend.service;

import org.example.backend.exceptions.HallNotFoundException;
import org.example.backend.model.CinemaHall;
import org.example.backend.model.CinemaHallDto;
import org.example.backend.repo.HallRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> deleteHall(String id) {
        CinemaHall existingHall = repo.findById(id)
                .orElseThrow(()-> new HallNotFoundException("Hall not found with id: " + id));
        repo.deleteById(existingHall.id());
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted.");
    }

    public CinemaHall getHallById(String id) {
        return repo.findById(id).orElseThrow(()-> new HallNotFoundException("Hall not found with id: " + id));
    }

    public CinemaHall editExistingHall(String id, CinemaHallDto updatedHall) {
        CinemaHall hall = repo.findById(id).orElseThrow(()-> new HallNotFoundException("Hall not found with id: " + id));

        CinemaHall newHall = new CinemaHall(id, updatedHall.name(), updatedHall.rows(), updatedHall.seatsPerRow());
        repo.save(newHall);
        return newHall;
    }
}

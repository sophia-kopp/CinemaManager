package org.example.backend.controller;

import org.example.backend.model.CinemaHall;
import org.example.backend.model.CinemaHallDto;
import org.example.backend.service.HallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {

    private final HallService service;

    public HallController(HallService service) {
        this.service = service;
    }

    @GetMapping
    public List<CinemaHall> getAllHalls(){
        return service.getAllHalls();
    }

    @PostMapping
    public CinemaHall addNewHall(@RequestBody CinemaHallDto hallDto){
        return service.addNewHall(hallDto);
    }
}

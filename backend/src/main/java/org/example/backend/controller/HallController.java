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

    @GetMapping("/{id}")
    public CinemaHall getHallById(@PathVariable String id){
        return service.getHallById(id);
    }

    @PostMapping
    public CinemaHall addNewHall(@RequestBody CinemaHallDto hallDto){
        return service.addNewHall(hallDto);
    }

    @PutMapping("/{id}")
    public CinemaHall editExistingHall(@PathVariable String id, @RequestBody CinemaHallDto hallDto){
        return service.editExistingHall(id, hallDto);
    }

    @DeleteMapping("/{id}")
    public void deleteHall(@PathVariable String id){
        service.deleteHall(id);
    }
}

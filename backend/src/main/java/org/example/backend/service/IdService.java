package org.example.backend.service;

import org.example.backend.repo.HallRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdService {

    private final HallRepo hallRepo;

    public IdService(HallRepo hallRepo) {
        this.hallRepo = hallRepo;
    }

    public String generateUUid(){
        return UUID.randomUUID().toString();
    }

    public String getIdForHalls(){
        int currentAmountOfHalls = hallRepo.findAll().size();
        String newHallId = String.valueOf(currentAmountOfHalls +1);
        return newHallId;
    }
}

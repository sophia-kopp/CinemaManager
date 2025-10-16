package org.example.backend.service;

import org.example.backend.repo.HallRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class IdServiceTest {

    HallRepo mockHallRepo = mock(HallRepo.class);
    IdService idService = new IdService(mockHallRepo);

    @Test
    void randomIdTest_shouldGenerateRandomId(){
        assertNotNull(idService.generateUUid());
    }
}
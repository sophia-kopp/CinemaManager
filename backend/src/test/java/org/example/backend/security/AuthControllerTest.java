package org.example.backend.security;

import org.example.backend.model.UserRole;
import org.example.backend.repo.ReservationRepo;
import org.example.backend.security.user.AppUser;
import org.example.backend.security.user.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppUserRepository repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

//    @Test
//    void getExample() throws Exception {
//
//        AppUser appUser = AppUser.builder().id("testUser").role(UserRole.GUEST).username("testUser").build();
//
//        repo.save(appUser);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/me")
//                        .with(oidcLogin().userInfoToken(token ->
//                                token.claim("login", "testUser"))))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(
//                        """
//                                {"id": "testUser"}
//"""
//                ))
//        ;
//
//
//    }

}
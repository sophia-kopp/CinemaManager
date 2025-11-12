package org.example.backend.security;

import org.example.backend.security.user.AppUser;
import org.example.backend.security.user.AppUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserService service;

    public AuthController(AppUserService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public AppUser getMe(@AuthenticationPrincipal OAuth2User user){
        return service.getUser(user);
    }
}

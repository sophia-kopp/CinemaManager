package org.example.backend.security.user;

import org.example.backend.exceptions.UserNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository userRepo;

    public AppUserService(AppUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public AppUser getUser(OAuth2User user) {
        return userRepo.findById(user.getName()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + user.getAttribute("login")));
    }
}

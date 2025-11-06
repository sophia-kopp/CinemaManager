package org.example.backend.security.user;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AppUserRepository userRepo;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        AppUser appUser = userRepo.findById(oAuth2User.getName())
                .orElseGet(() -> createAppUser(oAuth2User));
        return new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(appUser.role().toString())),

                oAuth2User.getAttributes(), "id");
    }


    private AppUser createAppUser(OAuth2User oAuth2User) {
        AppUser newAppUser = AppUser.builder()
                .id(oAuth2User.getName()) //userId vo github
                .username(oAuth2User.getAttribute("login"))
                .role(UserRole.GUEST)
                .build();
        userRepo.save(newAppUser);
        return newAppUser;
    }
}

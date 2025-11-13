package org.example.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.url}")
    private String sucessUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/api/halls").hasAuthority("ADMIN")
                        .requestMatchers("/api/movies/*").hasAuthority("ADMIN")
                        .requestMatchers("/api/favouriteMovies").hasAuthority("ADMIN")
                        .requestMatchers("/api/reservations").authenticated()
                        .requestMatchers("/api/reservations/*").hasAuthority("ADMIN")
                        .requestMatchers("/api/reservations/*").hasAuthority("GUEST")
                        .requestMatchers(HttpMethod.GET, "/api/presentations").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/presentations").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/presentations/*").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/presentations/*").hasAuthority("ADMIN")
                        .requestMatchers("/api/presentations/").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                )
                .logout(l-> l.logoutSuccessUrl(sucessUrl))
                .oauth2Login(o -> o
                        .defaultSuccessUrl(sucessUrl));
        return http.build();
    }
}

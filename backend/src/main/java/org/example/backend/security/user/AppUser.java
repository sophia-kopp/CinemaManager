package org.example.backend.security.user;

import lombok.Builder;
import org.example.backend.model.UserRole;

@Builder
public record AppUser(String id, String username, UserRole role) {
}

package org.example.backend.model;

public enum UserRole {
    ADMIN("admin"),
    GUEST("guest");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }
}

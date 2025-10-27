package org.example.backend.exceptions;

public class PresentationNotFoundException extends RuntimeException {
    public PresentationNotFoundException(String message) {
        super(message);
    }
}

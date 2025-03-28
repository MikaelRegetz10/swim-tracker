package com.swimtracker.swimtracker.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message + " não encontrado");
    }
}

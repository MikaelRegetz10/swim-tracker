package com.swimtracker.swimtracker.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuario nao encontrado, verifique o login.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

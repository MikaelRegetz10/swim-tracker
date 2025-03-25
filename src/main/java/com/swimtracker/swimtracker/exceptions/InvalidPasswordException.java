package com.swimtracker.swimtracker.exceptions;


public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() { super("Senha invalida");}
    public InvalidPasswordException(String message) {
        super(message);
    }
}
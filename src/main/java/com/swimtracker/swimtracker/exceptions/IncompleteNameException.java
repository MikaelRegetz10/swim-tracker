package com.swimtracker.swimtracker.exceptions;

public class IncompleteNameException extends RuntimeException {
    public IncompleteNameException(){ super("O nome completo deve ter pelo menos nome e sobrenome"); }
    public IncompleteNameException(String message) {
        super(message);
    }
}

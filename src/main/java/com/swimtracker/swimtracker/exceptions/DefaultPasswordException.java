package com.swimtracker.swimtracker.exceptions;

public class DefaultPasswordException extends RuntimeException {

    public DefaultPasswordException() { super("Altere sua senha para acessar o sistema.");}

    public DefaultPasswordException(String message) {
        super(message);
    }
}

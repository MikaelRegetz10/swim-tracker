package com.swimtracker.swimtracker.exceptions;

public class HaveEqualLoginException extends RuntimeException {
    public HaveEqualLoginException() {
    super("Login deve ser unico para cada usuario.");
  }

    public HaveEqualLoginException(String message) {
        super(message);
    }
}

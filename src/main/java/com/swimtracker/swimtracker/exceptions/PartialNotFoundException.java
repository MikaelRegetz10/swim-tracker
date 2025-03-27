package com.swimtracker.swimtracker.exceptions;

public class PartialNotFoundException extends RuntimeException {

  public PartialNotFoundException() {
    super("Parcial nao encontrada.");
  }


  public PartialNotFoundException(String message) {
    super(message);
  }
}

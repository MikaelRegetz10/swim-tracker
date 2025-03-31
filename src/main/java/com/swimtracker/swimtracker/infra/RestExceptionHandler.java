package com.swimtracker.swimtracker.infra;

import com.swimtracker.swimtracker.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DefaultPasswordException.class)
    private ResponseEntity<RestResponseMessage> defaultPasswordHandler(DefaultPasswordException ex) {
        RestResponseMessage response = new RestResponseMessage(HttpStatus.FORBIDDEN, ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    private ResponseEntity<RestResponseMessage> invalidPasswordHandler(InvalidPasswordException ex) {
        RestResponseMessage response = new RestResponseMessage(HttpStatus.FORBIDDEN, ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(IncompleteNameException.class)
    private ResponseEntity<RestResponseMessage> incompleteNameHandler(IncompleteNameException ex) {
        RestResponseMessage response = new RestResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestResponseMessage> notFoundHandler(NotFoundException ex) {
        RestResponseMessage response = new RestResponseMessage(HttpStatus.NOT_FOUND, ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(HaveEqualLoginException.class)
    private ResponseEntity<RestResponseMessage> haveEqualLoginHandler(HaveEqualLoginException ex) {
        RestResponseMessage response = new RestResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestResponseMessage> userNotFoundHandler(UserNotFoundException ex) {
        RestResponseMessage response = new RestResponseMessage(HttpStatus.NOT_FOUND, ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}



package com.swimtracker.swimtracker.infra;

import com.swimtracker.swimtracker.exceptions.DefaultPasswordException;
import com.swimtracker.swimtracker.exceptions.IncompleteNameException;
import com.swimtracker.swimtracker.exceptions.InvalidPasswordException;
import com.swimtracker.swimtracker.exceptions.PartialNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(DefaultPasswordException.class)
    private ResponseEntity<RestErrorMessage> defaultPasswordHandler(DefaultPasswordException ex) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.FORBIDDEN, ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    private ResponseEntity<RestErrorMessage> invalidPasswordHandler(InvalidPasswordException ex) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.FORBIDDEN, ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(IncompleteNameException.class)
    private ResponseEntity<RestErrorMessage> incompleteNameHandler(IncompleteNameException ex) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PartialNotFoundException.class)
    private ResponseEntity<RestErrorMessage> partialNotFoundHandler(PartialNotFoundException ex) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}

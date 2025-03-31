package com.swimtracker.swimtracker.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@Setter
public class RestResponseMessage {
    private HttpStatus status;
    private String message;
    private Integer code;
}

package com.roiceee.quotejokeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongTypeException extends RuntimeException{
    public WrongTypeException(String message) {
        super(message);
    }
    public WrongTypeException() {
        super("Invalid value for request parameter 'type'.");
    }
}

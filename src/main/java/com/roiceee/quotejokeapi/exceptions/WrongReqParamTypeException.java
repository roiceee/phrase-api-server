package com.roiceee.quotejokeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongReqParamTypeException extends RuntimeException{
    public WrongReqParamTypeException(String message) {
        super(message);
    }
    public WrongReqParamTypeException() {
        super("Invalid value for request parameter 'type'.");
    }
}

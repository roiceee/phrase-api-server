package com.roiceee.quotejokeapi.exceptions;

import com.roiceee.quotejokeapi.util.Params;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParamTypeValueException extends RuntimeException{
    public InvalidParamTypeValueException(String type) {
        super("Invalid value '" + type + "' for parameter '" + Params.TYPE + "'.");
    }
}

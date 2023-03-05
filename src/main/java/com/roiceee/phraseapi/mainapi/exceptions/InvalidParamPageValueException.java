package com.roiceee.phraseapi.mainapi.exceptions;

import com.roiceee.phraseapi.mainapi.util.Params;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParamPageValueException extends RuntimeException{

    public InvalidParamPageValueException(int value) {
        super("Invalid value '" + value + "' for parameter '" + Params.PAGE + "'.");
    }

}

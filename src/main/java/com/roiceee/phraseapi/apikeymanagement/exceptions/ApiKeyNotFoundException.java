package com.roiceee.phraseapi.apikeymanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiKeyNotFoundException extends RuntimeException{
    public ApiKeyNotFoundException() {
        super("No API key found.");
    }
}

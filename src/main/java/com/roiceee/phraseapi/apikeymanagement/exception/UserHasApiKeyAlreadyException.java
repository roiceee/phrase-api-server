package com.roiceee.phraseapi.apikeymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserHasApiKeyAlreadyException extends RuntimeException {

    public UserHasApiKeyAlreadyException() {
        super("User already has an existing API Key.");
    }
}

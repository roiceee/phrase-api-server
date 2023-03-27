package com.roiceee.phraseapi.resourceapi.exceptions;


public class TooManyRequestsException extends RuntimeException {
    public TooManyRequestsException() {
        super("API request limit reached within a certain duration.");
    }
}

package com.roiceee.phraseapi.resourceapi.exception;


public class TooManyRequestsException extends RuntimeException {
    public TooManyRequestsException() {
        super("API request limit reached within a certain duration.");
    }
}

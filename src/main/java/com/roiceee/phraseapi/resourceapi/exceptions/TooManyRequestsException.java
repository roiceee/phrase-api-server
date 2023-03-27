package com.roiceee.phraseapi.resourceapi.exceptions;


public class TooManyRequestsException extends RuntimeException {
    public TooManyRequestsException() {
        super("API rate limit reached within a certain duration.");
    }
}

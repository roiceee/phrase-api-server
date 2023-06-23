package com.roiceee.phraseapi.phrasemanagement.exception;

public class MaxPhrasesLimitReachedException extends RuntimeException {
    public MaxPhrasesLimitReachedException() {
        super("Max phrases limit reached.");
    }
}

package com.roiceee.phraseapi.phrasemanagement.exceptions;

public class MaxPhrasesLimitReachedException extends RuntimeException {
    public MaxPhrasesLimitReachedException() {
        super("Max phrases limit reached.");
    }
}

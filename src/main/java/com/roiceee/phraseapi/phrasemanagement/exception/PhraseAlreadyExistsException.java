package com.roiceee.phraseapi.phrasemanagement.exception;


public class PhraseAlreadyExistsException extends RuntimeException {
    public PhraseAlreadyExistsException() {
        super("Phrase already exists!");
    }
}

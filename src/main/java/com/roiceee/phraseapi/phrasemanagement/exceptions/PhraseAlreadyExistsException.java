package com.roiceee.phraseapi.phrasemanagement.exceptions;


public class PhraseAlreadyExistsException extends RuntimeException {
    public PhraseAlreadyExistsException() {
        super("Phrase already exists!");
    }
}

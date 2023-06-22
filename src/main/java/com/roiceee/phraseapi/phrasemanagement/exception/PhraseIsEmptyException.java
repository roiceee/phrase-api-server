package com.roiceee.phraseapi.phrasemanagement.exception;

public class PhraseIsEmptyException extends RuntimeException {
    public PhraseIsEmptyException() {
        super("Phrase should not be empty.");
    }
}

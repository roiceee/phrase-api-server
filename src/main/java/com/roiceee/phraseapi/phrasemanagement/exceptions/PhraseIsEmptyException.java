package com.roiceee.phraseapi.phrasemanagement.exceptions;

public class PhraseIsEmptyException extends RuntimeException {
    public PhraseIsEmptyException() {
        super("Phrase should not be empty.");
    }
}

package com.roiceee.phraseapi.phrasemanagement.exception;

public class PhraseNotFoundException extends RuntimeException{
    public PhraseNotFoundException() {
        super("Phrase not found");
    }
}

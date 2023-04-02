package com.roiceee.phraseapi.phrasemanagement.exceptions;

public class PhraseNotFoundException extends RuntimeException{
    public PhraseNotFoundException() {
        super("Phrase not found");
    }
}

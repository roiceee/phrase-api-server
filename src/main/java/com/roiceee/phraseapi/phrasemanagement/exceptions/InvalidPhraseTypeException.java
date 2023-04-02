package com.roiceee.phraseapi.phrasemanagement.exceptions;

public class InvalidPhraseTypeException extends RuntimeException{
    public InvalidPhraseTypeException() {
        super("Invalid value for phrase type");
    }
}

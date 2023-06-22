package com.roiceee.phraseapi.phrasemanagement.exception;

public class InvalidPhraseTypeException extends RuntimeException{
    public InvalidPhraseTypeException() {
        super("Invalid value for phrase type");
    }
}

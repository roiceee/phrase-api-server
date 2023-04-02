package com.roiceee.phraseapi.phrasemanagement.exceptions;

import com.roiceee.phraseapi.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PhraseManagementExceptionAdvice {

    @ExceptionHandler(PhraseAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> catchPhraseAlreadyExistsException(PhraseAlreadyExistsException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(InvalidPhraseTypeException.class)
    public ResponseEntity<ErrorMessage> catchInvalidPhraseTypeException(InvalidPhraseTypeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(PhraseNotFoundException.class)
    public ResponseEntity<ErrorMessage> catchPhraseNotFoundException(PhraseNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }
    @ExceptionHandler(PhraseIsEmptyException.class)
    public ResponseEntity<ErrorMessage> catchPhraseIsEmptyException(PhraseIsEmptyException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(MaxPhrasesLimitReachedException.class)
    public ResponseEntity<ErrorMessage> catchPhraseIsEmptyException(MaxPhrasesLimitReachedException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }
}

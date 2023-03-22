package com.roiceee.phraseapi.apikeymanagement.exceptions;

import com.roiceee.phraseapi.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ApiKeyNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleApiKeyNotFoundException(ApiKeyNotFoundException e) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());

        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(UserHasApiKeyAlreadyException.class)
    public ResponseEntity<ErrorMessage> handleUserHasApiKeyAlreadyException(UserHasApiKeyAlreadyException e) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());

        return ResponseEntity.status(status).body(errorMessage);
    }
}

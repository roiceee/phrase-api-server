package com.roiceee.phraseapi.resourceapi.exceptions;

import com.roiceee.phraseapi.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceApiExceptionAdvice {

    @ExceptionHandler(InvalidParamValueException.class)
    public ResponseEntity<ErrorMessage> handleInvalidParamPageValueException(InvalidParamValueException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<ErrorMessage> handleTooManyRequestsException(TooManyRequestsException e) {
        HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;
        ErrorMessage errorMessage = new ErrorMessage(status.value(), status.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }

}

package com.roiceee.quotejokeapi.controllers.advice;

import com.roiceee.quotejokeapi.exceptions.WrongTypeException;
import com.roiceee.quotejokeapi.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(WrongTypeException.class)
    public ResponseEntity<ErrorMessage> wrongTypeException() {
        ErrorMessage errorMessage = new ErrorMessage("Invalid value for parameter 'type'.");
        return ResponseEntity.badRequest().body(errorMessage);
    }

}

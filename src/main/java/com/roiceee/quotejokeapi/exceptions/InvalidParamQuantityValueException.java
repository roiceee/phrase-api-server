package com.roiceee.quotejokeapi.exceptions;

import com.roiceee.quotejokeapi.util.ReqParamNames;

public class InvalidParamQuantityValueException extends RuntimeException{

    public InvalidParamQuantityValueException(int quantity) {
        super("Invalid value '" + quantity + "' for '" + ReqParamNames.qty + "'.");
    }

}

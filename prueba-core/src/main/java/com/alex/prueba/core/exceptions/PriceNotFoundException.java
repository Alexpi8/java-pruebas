package com.alex.prueba.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends Exception {
    public PriceNotFoundException(String message) {
        super(message);
    }
}

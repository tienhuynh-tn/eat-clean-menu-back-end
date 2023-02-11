package com.happy3friends.eatcleanmenubackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends Exception {

    public InternalServerException(String message) {
        super(message);
    }
}
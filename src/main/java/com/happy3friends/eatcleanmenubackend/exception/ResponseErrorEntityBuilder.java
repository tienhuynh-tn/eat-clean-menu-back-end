package com.happy3friends.eatcleanmenubackend.exception;

import org.springframework.http.ResponseEntity;

public class ResponseErrorEntityBuilder {

    public static ResponseEntity<Object> build(ErrorResponse error) {
        return new ResponseEntity<>(error, error.getHttpStatus());
    }
}
package com.happy3friends.eatcleanmenubackend.handler;

import com.happy3friends.eatcleanmenubackend.dto.ErrorResponseDTO;
import com.happy3friends.eatcleanmenubackend.exception.BadRequestException;
import com.happy3friends.eatcleanmenubackend.exception.InternalServerException;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
//import com.happy3friends.eatcleanmenubackend.exception.OAuth2AuthenticationProcessingException;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponseDTO> handleInternalServerException(Exception ex) {
        return ResponseEntityBuilder.generateErrorResponse("Internal Server Error!", HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(NotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        return ResponseEntityBuilder.generateErrorResponse("Resource Not Found!", HttpStatus.NOT_FOUND, details);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        return ResponseEntityBuilder.generateErrorResponse("Bad Requests!", HttpStatus.BAD_REQUEST, details);
    }

//    @ExceptionHandler(OAuth2AuthenticationProcessingException.class)
//    public ResponseEntity<ErrorResponseDTO> handleOAuth2AuthenticationProcessingException(OAuth2AuthenticationProcessingException ex) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getMessage());
//
//        return ResponseEntityBuilder.generateErrorResponse("Unauthorized", HttpStatus.UNAUTHORIZED, details);
//    }
}
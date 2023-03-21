package com.happy3friends.eatcleanmenubackend.handler;

import com.happy3friends.eatcleanmenubackend.dto.ErrorResponseDTO;
import com.happy3friends.eatcleanmenubackend.exception.BadRequestException;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleInternalServerException(Exception ex) {
        LOGGER.error("An exception occurred: ", ex);
        return ResponseEntityBuilder.generateErrorResponse("Internal Server Error!", HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList(String.valueOf(ex)));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        LOGGER.warn("Access Denied: " + details);
        return ResponseEntityBuilder.generateErrorResponse("Access Denied!", HttpStatus.FORBIDDEN, details);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(NotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        LOGGER.warn("Resource not found: " + details);
        return ResponseEntityBuilder.generateErrorResponse("Resource Not Found!", HttpStatus.NOT_FOUND, details);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        LOGGER.warn("An invalid request was rejected: " + details);
        return ResponseEntityBuilder.generateErrorResponse("Bad Requests!", HttpStatus.BAD_REQUEST, details);
    }
}
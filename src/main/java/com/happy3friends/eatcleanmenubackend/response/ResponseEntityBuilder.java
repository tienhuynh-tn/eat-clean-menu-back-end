package com.happy3friends.eatcleanmenubackend.response;

import com.happy3friends.eatcleanmenubackend.dto.ErrorResponseDTO;
import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.utils.DateTimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseEntityBuilder {

    public static <T> ResponseEntity<ResponseDTO<T>> generateResponse(String message, HttpStatus httpStatus, T responseObj) {
        ResponseDTO response = new ResponseDTO(message, httpStatus.value(), responseObj);

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<ErrorResponseDTO> generateErrorResponse(String message, HttpStatus httpStatus, List<String> errors) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                DateTimeUtil.getZoneDateTimeNow(),
                httpStatus.value(),
                message,
                errors
        );
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
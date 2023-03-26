package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class HomeController {

    @GetMapping(value = "/")
    public ResponseEntity<ResponseDTO<String>> home() {
        return ResponseEntityBuilder.generateResponse(
                "Successfully!",
                HttpStatus.OK,
                "Toilet Map API - tienhuynh-tn"
        );
    }
}

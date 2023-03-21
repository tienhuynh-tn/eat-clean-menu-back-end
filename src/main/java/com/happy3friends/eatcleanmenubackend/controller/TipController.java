package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.dto.TipDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tips")
public class TipController {

    @Autowired
    private TipService tipService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TipDTO>>> findAll() {

        List<TipDTO> tipDTOS = tipService.findAll();

        return ResponseEntityBuilder.generateResponse(
                "Find all tips successfully!",
                HttpStatus.OK,
                tipDTOS
        );
    }

    @GetMapping(value = "/{tipId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<TipDTO>> findById(
            @PathVariable("tipId") int tipId) {

        TipDTO tipDTO = tipService.findById(tipId);

        return ResponseEntityBuilder.generateResponse(
                "Find a tip by Id successfully!",
                HttpStatus.OK,
                tipDTO
        );
    }
}

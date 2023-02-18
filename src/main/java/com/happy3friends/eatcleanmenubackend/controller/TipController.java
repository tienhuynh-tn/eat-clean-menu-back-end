package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;
import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.dto.TipDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.TipService;
import io.swagger.annotations.*;
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
@RequestMapping("/tips")
@Api(value = "Tip API", description = "Provides Tip API's", tags = "Tip API")
public class TipController {

    @Autowired
    private TipService tipService;

    @ApiOperation(value = "Find all tips")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TipDTO>>> findAll() {

        List<TipDTO> tipDTOS = tipService.findAll();

        return ResponseEntityBuilder.generateResponse(
                "Find all tips successfully!",
                HttpStatus.OK,
                tipDTOS
        );
    }

    @ApiOperation(value = "Find a tip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/{tipId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<TipDTO>> findById(
            @ApiParam(value = "A specific tip id",
                    required = true,
                    example = "1")
            @PathVariable("tipId") int tipId) {

        TipDTO tipDTO = tipService.findById(tipId);

        return ResponseEntityBuilder.generateResponse(
                "Find a tip by Id successfully!",
                HttpStatus.OK,
                tipDTO
        );
    }
}

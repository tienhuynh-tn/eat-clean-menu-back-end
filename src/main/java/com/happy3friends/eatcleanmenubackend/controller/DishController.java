package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;
import com.happy3friends.eatcleanmenubackend.dto.IngredientDTO;
import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.DishService;
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
import java.util.Map;

@RestController
@RequestMapping("/dishes")
@Api(value = "Dish API", description = "Provides Dish API's", tags = "Dish API")
public class DishController {

    @Autowired
    private DishService dishService;

    @ApiOperation(value = "Find all dishes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<DishDTO>>> findAll() {

        List<DishDTO> dishDTOS = dishService.findAll();

        return ResponseEntityBuilder.generateResponse(
                "Find all dishes successfully!",
                HttpStatus.OK,
                dishDTOS
        );
    }

    @ApiOperation(value = "Find a dish")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/{dishId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<DishDTO>> findById(
            @ApiParam(value = "A specific dish id",
                    required = true,
                    example = "1")
            @PathVariable("dishId") int dishId) {

        DishDTO dishDTO = dishService.findById(dishId);

        return ResponseEntityBuilder.generateResponse(
                "Find a dish by Id successfully!",
                HttpStatus.OK,
                dishDTO
        );
    }

    @ApiOperation(value = "Find all ingredients of a dish")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Resource Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/{dishId}/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Map<String, List<IngredientDTO>>>> findIngredientsByDishId(
            @ApiParam(value = "A specific dish id",
                    required = true,
                    example = "1")
            @PathVariable("dishId") int dishId) {

        Map<String, List<IngredientDTO>> ingredients = dishService.findIngredientsByDishId(dishId);

        return ResponseEntityBuilder.generateResponse(
                "Find a dish by Id successfully!",
                HttpStatus.OK,
                ingredients
        );
    }
}
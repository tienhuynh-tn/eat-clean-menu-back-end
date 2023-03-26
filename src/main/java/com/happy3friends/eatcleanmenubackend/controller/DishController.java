package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;
import com.happy3friends.eatcleanmenubackend.dto.IngredientDTO;
import com.happy3friends.eatcleanmenubackend.dto.RecipeDTO;
import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.DishService;
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
@RequestMapping(value = "/api/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<DishDTO>>> findAll() {

        List<DishDTO> dishDTOS = dishService.findAll();

        return ResponseEntityBuilder.generateResponse(
                "Find all dishes successfully!",
                HttpStatus.OK,
                dishDTOS
        );
    }

    @GetMapping(value = "/{dishId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<DishDTO>> findById(
            @PathVariable("dishId") int dishId) {

        DishDTO dishDTO = dishService.findById(dishId);

        return ResponseEntityBuilder.generateResponse(
                "Find a dish by Id successfully!",
                HttpStatus.OK,
                dishDTO
        );
    }

    @GetMapping(value = "/{dishId}/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Map<String, List<IngredientDTO>>>> findIngredientsByDishId(
            @PathVariable("dishId") int dishId) {

        Map<String, List<IngredientDTO>> ingredients = dishService.findIngredientsByDishId(dishId);

        return ResponseEntityBuilder.generateResponse(
                "Find all ingredients of a dish successfully!",
                HttpStatus.OK,
                ingredients
        );
    }

    @GetMapping(value = "/{dishId}/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<RecipeDTO>>> findRecipesByDishId(
            @PathVariable("dishId") int dishId) {

        List<RecipeDTO> recipes = dishService.findRecipesByDishId(dishId);

        return ResponseEntityBuilder.generateResponse(
                "Find all recipes of a dish successfully!",
                HttpStatus.OK,
                recipes
        );
    }
}
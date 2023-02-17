package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.MenuDishDTO;
import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
//import com.happy3friends.eatcleanmenubackend.security.TokenProvider;
import com.happy3friends.eatcleanmenubackend.service.MenuDishService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/menu-dishes")
@Api(value = "Menu with Dishes API", description = "Provides Menu with Dishes API's", tags = "Menu Dish API")
public class MenuDishController {

//    @Autowired
//    private TokenProvider tokenProvider;

    @Autowired
    private MenuDishService menuDishService;

    @ApiOperation(value = "Get all dishes in Menu of a date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Resource Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/mealDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<MenuDishDTO>>> getMenuDishByMealDateAndUserId(
//            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "UserId", example = "1")
            @RequestParam(name = "userId") int userId,
            @ApiParam(value = "Meal Date", example = "2023-02-18")
            @RequestParam(name = "mealDate") String mealDate) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        List<MenuDishDTO> menuDishEntity = menuDishService.getMenuDishByMealDateAndUserId(mealDate, userId);

        return ResponseEntityBuilder.generateResponse(
                "Get all dishes in Menu at " + mealDate + " successfully!",
                HttpStatus.OK,
                menuDishEntity
        );
    }
}

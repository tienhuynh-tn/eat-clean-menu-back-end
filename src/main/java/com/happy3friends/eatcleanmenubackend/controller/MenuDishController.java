package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.MenuDishDTO;
import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.MenuDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/menu-dishes")
public class MenuDishController {

    @Autowired
    private MenuDishService menuDishService;

    @GetMapping(value = "/mealDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<MenuDishDTO>>> getMenuDishByMealDateAndUserId(
            @RequestParam(name = "userId") int userId,
            @RequestParam(name = "mealDate") String mealDate) {

        List<MenuDishDTO> menuDishEntity = menuDishService.getMenuDishByMealDateAndUserId(mealDate, userId);

        return ResponseEntityBuilder.generateResponse(
                "Get all dishes in Menu at " + mealDate + " successfully!",
                HttpStatus.OK,
                menuDishEntity
        );
    }
}

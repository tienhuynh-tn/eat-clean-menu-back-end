package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.CustomUserDietaryInfoDTO;
import com.happy3friends.eatcleanmenubackend.service.UserDietaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user-dietary-info")
public class UserDietaryInfoController {

    @Autowired
    private UserDietaryInfoService userDietaryInfoService;

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @PathVariable("userId") int userId,
            @RequestBody CustomUserDietaryInfoDTO customUserDietaryInfoDTO) {

        userDietaryInfoService.createUserDietaryInfoByUserId(customUserDietaryInfoDTO, userId);
    }
}

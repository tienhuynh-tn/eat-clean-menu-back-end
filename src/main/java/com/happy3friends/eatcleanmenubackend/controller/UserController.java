package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.dto.UserDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Map<String, Integer>>> login(
            @RequestBody UserDTO user) {

        int userId = userService.login(user);

        Map<String, Integer> response = new HashMap<>();
        response.put("userId", userId);

        return ResponseEntityBuilder.generateResponse(
                "Login successfully!",
                HttpStatus.OK,
                response
        );
    }

    @GetMapping(value = "/{gmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<UserDTO>> get(
            @PathVariable String gmail) {

        UserDTO response = userService.get(gmail);

        return ResponseEntityBuilder.generateResponse(
                "Login successfully!",
                HttpStatus.OK,
                response
        );
    }

    @PutMapping(value = "/{userId}/subscriptionType")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createUserSubscription(
            @PathVariable("userId") int userId,
            @RequestParam(name = "subscriptionType") String subscriptionType) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        userService.createUserSubscriptionByUserId(subscriptionType, userId);
    }
}

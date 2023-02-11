package com.happy3friends.eatcleanmenubackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api(value = "User API", description = "Provides all User's APIs", tags = "User API")
public class UserController {

    /*@ApiOperation(value = "Check specific users already been in the system")
    @PostMapping(value = "/check-login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkLogin() {
        return ResponseEntity.ok();
    }*/

}

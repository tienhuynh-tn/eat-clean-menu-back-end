package com.happy3friends.eatcleanmenubackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Home API", description = "Home API", tags = "Home API")
public class HomeController {

    @ApiOperation(value = "Home")
    @GetMapping(value = "/")
    public String home() {
        return "Hello 123 Eat Clean Menu - tienhuynh-tn";
    }
}

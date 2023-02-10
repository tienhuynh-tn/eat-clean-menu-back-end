package com.happy3friends.eatcleanmenubackend.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
@Api(value = "Test API", description = "Test API", tags = "Test API")
public class TestController {

    @ApiOperation(value = "Test")
    @GetMapping(value = "/")
    public String test() {
        return "Test";
    }
}
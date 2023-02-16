package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.dto.UserDietaryInfoDTO;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.security.TokenProvider;
import com.happy3friends.eatcleanmenubackend.service.UserDietaryInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user-dietary-info")
@Api(value = "User Dietary Information API", description = "Provides User Dietary Information API's", tags = "User Dietary Information API")
public class UserDietaryInfoController {

    @Autowired
    private UserDietaryInfoService userDietaryInfoService;

    @Autowired
    private TokenProvider tokenProvider;

    @ApiOperation(value = "Create User Dietary Information")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<UserDietaryInfoDTO>> create(
            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "A JSON value representing a User Dietary Info.")
            @RequestBody UserDietaryInfoDTO userDietaryInfoDTO) {

        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        userDietaryInfoService.createUserDietaryInfoByUserId(userDietaryInfoDTO, userId);

        return ResponseEntityBuilder.generateResponse(
                "Create User Dietary Information successfully!",
                HttpStatus.CREATED,
                userDietaryInfoDTO
        );
    }
}

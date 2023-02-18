package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.request.CustomUserDietaryTrackingRequest;
import com.happy3friends.eatcleanmenubackend.service.UserDietaryTrackingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-dietary-tracking")
@Api(value = "User Dietary Tracking API", description = "Provides User Dietary Tracking API's", tags = "User Dietary Tracking API")
public class UserDietaryTrackingController {

    @Autowired
    private UserDietaryTrackingService userDietaryTrackingService;

    @ApiOperation(value = "Create User Dietary Tracking Calories")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "/{userId}/user-calories", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserCaloriesTracking(
//            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "UserId", example = "3")
            @PathVariable("userId") int userId,
            @ApiParam(value = "Information for user dietary tracking")
            @RequestBody CustomUserDietaryTrackingRequest request) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        userDietaryTrackingService.createUserCaloriesTracking(userId, request.getUserCalories(), request.getMenuDishId());
    }

    @ApiOperation(value = "Create User Dietary Tracking Weight")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "/{userId}/user-weight")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserWeightTracking(
//            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "UserId", example = "3")
            @PathVariable("userId") int userId,
            @ApiParam(value = "User Weight", example = "55")
            @RequestParam(value = "weight") double weight) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        userDietaryTrackingService.createUserWeightTracking(userId, weight);
    }
}

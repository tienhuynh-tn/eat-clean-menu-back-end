package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.request.CustomUserDietaryTrackingRequest;
import com.happy3friends.eatcleanmenubackend.response.CustomUserDietaryTrackingResponse;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.UserDietaryTrackingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "Create User Dietary Tracking Calories By Date")
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

    @ApiOperation(value = "Get User Dietary Tracking Calories By Date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/{userId}/user-calories/date")
    public ResponseEntity<ResponseDTO<List<CustomUserDietaryTrackingResponse>>> getUserCaloriesTrackingByDate(
//            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "UserId", example = "3")
            @PathVariable("userId") int userId) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));


        List<CustomUserDietaryTrackingResponse> customUserDietaryTrackingResponse = userDietaryTrackingService.getTrackingCaloriesByDate(userId);

        return ResponseEntityBuilder.generateResponse(
                "Get user calories tracking by date successfully!",
                HttpStatus.OK,
                customUserDietaryTrackingResponse
        );
    }

    @ApiOperation(value = "Get User Dietary Tracking Calories By Week")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/{userId}/user-calories/week")
    public ResponseEntity<ResponseDTO<List<CustomUserDietaryTrackingResponse>>> getUserCaloriesTrackingByWeek(
//            @RequestHeader("Authorization") @ApiIgnore String bearerToken,
            @ApiParam(value = "UserId", example = "3")
            @PathVariable("userId") int userId) {

//        int userId = tokenProvider.getUserIdFromToken(tokenProvider.getTokenFromBearerToken(bearerToken));

        List<CustomUserDietaryTrackingResponse> customUserDietaryTrackingResponse = userDietaryTrackingService.getTrackingCaloriesByWeek(userId);

        return ResponseEntityBuilder.generateResponse(
                "Get user calories tracking by week successfully!",
                HttpStatus.OK,
                customUserDietaryTrackingResponse
        );
    }
}

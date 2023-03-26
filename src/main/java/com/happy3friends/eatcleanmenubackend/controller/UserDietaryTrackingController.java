package com.happy3friends.eatcleanmenubackend.controller;

import com.happy3friends.eatcleanmenubackend.dto.ResponseDTO;
import com.happy3friends.eatcleanmenubackend.request.CustomUserDietaryTrackingRequest;
import com.happy3friends.eatcleanmenubackend.response.CustomUserDietaryTrackingResponse;
import com.happy3friends.eatcleanmenubackend.response.ResponseEntityBuilder;
import com.happy3friends.eatcleanmenubackend.service.UserDietaryTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user-dietary-tracking")
public class UserDietaryTrackingController {

    @Autowired
    private UserDietaryTrackingService userDietaryTrackingService;

    @PostMapping(value = "/{userId}/user-calories", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserCaloriesTracking(
            @PathVariable("userId") int userId,
            @RequestBody CustomUserDietaryTrackingRequest request) {

        userDietaryTrackingService.createUserCaloriesTracking(userId, request.getUserCalories(), request.getMenuDishId());
    }

    @PostMapping(value = "/{userId}/user-weight")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserWeightTracking(
            @PathVariable("userId") int userId,
            @RequestParam(value = "weight") double weight) {

        userDietaryTrackingService.createUserWeightTracking(userId, weight);
    }

    @GetMapping(value = "/{userId}/user-calories/date")
    public ResponseEntity<ResponseDTO<List<CustomUserDietaryTrackingResponse>>> getUserCaloriesTrackingByDate(
            @PathVariable("userId") int userId) {

        List<CustomUserDietaryTrackingResponse> customUserDietaryTrackingResponse = userDietaryTrackingService.getTrackingCaloriesByDate(userId);

        return ResponseEntityBuilder.generateResponse(
                "Get user calories tracking by date successfully!",
                HttpStatus.OK,
                customUserDietaryTrackingResponse
        );
    }

    @GetMapping(value = "/{userId}/user-calories/week")
    public ResponseEntity<ResponseDTO<List<CustomUserDietaryTrackingResponse>>> getUserCaloriesTrackingByWeek(
            @PathVariable("userId") int userId) {

        List<CustomUserDietaryTrackingResponse> customUserDietaryTrackingResponse = userDietaryTrackingService.getTrackingCaloriesByWeek(userId);

        return ResponseEntityBuilder.generateResponse(
                "Get user calories tracking by week successfully!",
                HttpStatus.OK,
                customUserDietaryTrackingResponse
        );
    }

    @GetMapping(value = "/{userId}/user-calories/month")
    public ResponseEntity<ResponseDTO<CustomUserDietaryTrackingResponse>> getUserCaloriesTrackingByMonth(
            @PathVariable("userId") int userId) {

        CustomUserDietaryTrackingResponse customUserDietaryTrackingResponse = userDietaryTrackingService.getTrackingCaloriesByMonth(userId);

        return ResponseEntityBuilder.generateResponse(
                "Get user calories tracking by month successfully!",
                HttpStatus.OK,
                customUserDietaryTrackingResponse
        );
    }
}

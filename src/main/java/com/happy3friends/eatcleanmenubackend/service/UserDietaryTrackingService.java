package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.CustomUserDietaryTrackingDTO;
import com.happy3friends.eatcleanmenubackend.response.CustomUserDietaryTrackingResponse;

import java.util.List;

public interface UserDietaryTrackingService {
    void createUserCaloriesTracking(int userId, double calories, int menuDishId);
    void createUserWeightTracking(int userId, double weight);
    List<CustomUserDietaryTrackingResponse> getTrackingCaloriesByDate(int userId);
}

package com.happy3friends.eatcleanmenubackend.service;

public interface UserDietaryTrackingService {
    void createUserCaloriesTracking(int userId, double calories, int menuDishId);
    void createUserWeightTracking(int userId, double weight);
}

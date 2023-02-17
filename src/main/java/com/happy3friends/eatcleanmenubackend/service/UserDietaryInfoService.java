package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.UserDietaryInfoDTO;

public interface UserDietaryInfoService {
    void createUserDietaryInfoByUserId(UserDietaryInfoDTO userDietaryInfoDTO, int userId);
    double calculateCaloriesConsumedByBmrAndActivityRateAndDietTarget(double bmr, String activityRate, String dietTarget);
}

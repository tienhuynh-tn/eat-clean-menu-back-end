package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.CustomUserDietaryInfoDTO;
import com.happy3friends.eatcleanmenubackend.dto.UserDietaryInfoDTO;

public interface UserDietaryInfoService {
    void createUserDietaryInfoByUserId(CustomUserDietaryInfoDTO customUserDietaryInfoDTO, int userId);
    double calculateCaloriesConsumedByBmrAndActivityRateAndDietTarget(double bmr, String activityRate, String dietTarget);
}

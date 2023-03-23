package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.CustomUserDietaryInfoDTO;

public interface UserDietaryInfoService {
    void createUserDietaryInfoByUserId(CustomUserDietaryInfoDTO customUserDietaryInfoDTO, int userId);
}

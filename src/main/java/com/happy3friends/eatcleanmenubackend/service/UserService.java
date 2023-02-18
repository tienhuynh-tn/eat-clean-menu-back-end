package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.UserDTO;

public interface UserService {
    void createUserSubscriptionByUserId(String subscriptionType, int userId);
    int login(UserDTO user);
}

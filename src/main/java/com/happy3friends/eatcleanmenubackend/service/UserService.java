package com.happy3friends.eatcleanmenubackend.service;

public interface UserService {
    void createUserSubscriptionByUserId(String subscriptionType, int userId);
}

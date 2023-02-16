package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.entity.UsersEntity;
import com.happy3friends.eatcleanmenubackend.repository.UserRepository;
import com.happy3friends.eatcleanmenubackend.service.UserService;
import com.happy3friends.eatcleanmenubackend.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUserSubscriptionByUserId(String subscriptionType, int userId) {
        Optional<UsersEntity> existed = userRepository.findById(userId);

        existed.get().setSubscriptionType(subscriptionType);
        existed.get().setSubscriptionDate(new Date(DateTimeUtil.getDateNow().getTime()));

        userRepository.save(existed.get());
    }
}

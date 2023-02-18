package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.entity.MenuDishEntity;
import com.happy3friends.eatcleanmenubackend.entity.UserDietaryTrackingEntity;
import com.happy3friends.eatcleanmenubackend.entity.UsersEntity;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
import com.happy3friends.eatcleanmenubackend.repository.MenuDishRepository;
import com.happy3friends.eatcleanmenubackend.repository.UserDietaryTrackingRepository;
import com.happy3friends.eatcleanmenubackend.repository.UserRepository;
import com.happy3friends.eatcleanmenubackend.service.UserDietaryTrackingService;
import com.happy3friends.eatcleanmenubackend.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserDietaryTrackingServiceImpl implements UserDietaryTrackingService {

    @Autowired
    private UserDietaryTrackingRepository userDietaryTrackingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuDishRepository menuDishRepository;

    @Override
    public void createUserCaloriesTracking(int userId, double calories, int menuDishId) {
        Optional<UsersEntity> users = userRepository.findById(userId);

        if (users.isPresent())
            if (users.get() == null) throw new NotFoundException("Can not find user with userId: " + userId);

        menuDishRepository.updateStatusById(menuDishId);

        UserDietaryTrackingEntity entity = new UserDietaryTrackingEntity();
        entity.setUserCalories(calories);
        entity.setUserId(userId);
        // TODO: set date from menu_dish and time depend on meal time
        entity.setDatetime(DateTimeUtil.getTimestampNow());
        userDietaryTrackingRepository.save(entity);
    }

    @Override
    public void createUserWeightTracking(int userId, double weight) {
        Optional<UsersEntity> users = userRepository.findById(userId);

        if (users.isPresent())
            if (users.get() == null) throw new NotFoundException("Can not find user with userId: " + userId);

        UserDietaryTrackingEntity entity = new UserDietaryTrackingEntity();
        entity.setUserWeight(weight);
        entity.setUserId(userId);
        // TODO: set date from menu_dish and time depend on meal time
        entity.setDatetime(DateTimeUtil.getTimestampNow());
        userDietaryTrackingRepository.save(entity);
    }
}

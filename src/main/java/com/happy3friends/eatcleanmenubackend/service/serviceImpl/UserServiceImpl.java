package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.entity.MenuEntity;
import com.happy3friends.eatcleanmenubackend.entity.UsersEntity;
import com.happy3friends.eatcleanmenubackend.exception.BadRequestException;
import com.happy3friends.eatcleanmenubackend.repository.MenuRepository;
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

    @Autowired
    MenuRepository menuRepository;

    @Override
    public void createUserSubscriptionByUserId(String subscriptionType, int userId) {
        MenuEntity menuEntity = menuRepository.findByUserId(userId);

        java.util.Date startDate = DateTimeUtil.getDateNow();
        java.util.Date endDate;
        switch (subscriptionType) {
            case "1 tháng":
                endDate = DateTimeUtil.addDays(startDate, 30);
                break;
            case "3 tháng":
                endDate = DateTimeUtil.addDays(startDate, 30 * 3);
                break;
            case "12 tháng":
                endDate = DateTimeUtil.addDays(startDate, 30 * 12);
                break;
            default:
                throw new BadRequestException("No subscription type: " + subscriptionType);
        }

        if (menuEntity == null) {
            MenuEntity createMenu = new MenuEntity();
            createMenu.setUserId(userId);
            createMenu.setMenuPeriodStartDate(new Date(startDate.getTime()));
            createMenu.setMenuPeriodEndDate(new Date(endDate.getTime()));
            menuRepository.save(createMenu);
        } else {
            menuEntity.setMenuPeriodStartDate(new Date(startDate.getTime()));
            menuEntity.setMenuPeriodEndDate(new Date(endDate.getTime()));
            menuRepository.save(menuEntity);
        }

        Optional<UsersEntity> existed = userRepository.findById(userId);
        existed.get().setSubscriptionType(subscriptionType);
        existed.get().setSubscriptionDate(new Date(DateTimeUtil.getDateNow().getTime()));
        userRepository.save(existed.get());
    }
}

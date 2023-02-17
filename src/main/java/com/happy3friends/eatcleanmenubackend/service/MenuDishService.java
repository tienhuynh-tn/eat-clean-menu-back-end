package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.MenuDishDTO;

import java.util.Date;
import java.util.List;

public interface MenuDishService {
    List<MenuDishDTO> createMenuDishByUserIdAndMealDateAndMenuId(int userId, Date mealDate, int menuId);
    List<MenuDishDTO> getMenuDishByMealDateAndUserId(String mealDate, int userId);
}

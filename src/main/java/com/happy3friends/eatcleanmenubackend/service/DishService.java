package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;

public interface DishService {
    DishDTO findById(int dishId);
}
package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;

import java.util.List;

public interface DishService {
    List<DishDTO> findAll();
    DishDTO findById(int dishId);
}
package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;
import com.happy3friends.eatcleanmenubackend.dto.IngredientDTO;

import java.util.List;
import java.util.Map;

public interface DishService {
    List<DishDTO> findAll();
    DishDTO findById(int dishId);
    Map<String, List<IngredientDTO>> findIngredientsByDishId(int dishId);
}
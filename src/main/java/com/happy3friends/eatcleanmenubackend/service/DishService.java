package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;
import com.happy3friends.eatcleanmenubackend.dto.IngredientDTO;
import com.happy3friends.eatcleanmenubackend.dto.RecipeDTO;

import java.util.List;
import java.util.Map;

public interface DishService {
    List<DishDTO> findAll();
    DishDTO findById(int dishId);
    Map<String, List<IngredientDTO>> findIngredientsByDishId(int dishId);
    List<RecipeDTO> findRecipesByDishId(int dishId);
}
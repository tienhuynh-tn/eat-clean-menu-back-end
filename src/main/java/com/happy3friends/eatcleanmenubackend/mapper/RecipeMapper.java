package com.happy3friends.eatcleanmenubackend.mapper;

import com.happy3friends.eatcleanmenubackend.dto.IngredientDTO;
import com.happy3friends.eatcleanmenubackend.dto.RecipeDTO;
import com.happy3friends.eatcleanmenubackend.entity.IngredientEntity;
import com.happy3friends.eatcleanmenubackend.entity.RecipeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RecipeMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RecipeDTO convertEntityToDTO(RecipeEntity recipeEntity) {
        return Objects.isNull(recipeEntity) ? null : modelMapper.map(recipeEntity, RecipeDTO.class);
    }

    public RecipeEntity convertDTOToEntity(RecipeDTO recipeDTO) {
        return Objects.isNull(recipeDTO) ? null : modelMapper.map(recipeDTO, RecipeEntity.class);
    }
}

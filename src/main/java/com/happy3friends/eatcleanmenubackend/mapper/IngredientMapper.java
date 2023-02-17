package com.happy3friends.eatcleanmenubackend.mapper;

import com.happy3friends.eatcleanmenubackend.dto.IngredientDTO;
import com.happy3friends.eatcleanmenubackend.entity.IngredientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class IngredientMapper {

    @Autowired
    private ModelMapper modelMapper;

    public IngredientDTO convertEntityToDTO(IngredientEntity ingredientEntity) {
        return Objects.isNull(ingredientEntity) ? null : modelMapper.map(ingredientEntity, IngredientDTO.class);
    }

    public IngredientEntity convertDTOToEntity(IngredientDTO ingredientDTO) {
        return Objects.isNull(ingredientDTO) ? null : modelMapper.map(ingredientDTO, IngredientEntity.class);
    }
}

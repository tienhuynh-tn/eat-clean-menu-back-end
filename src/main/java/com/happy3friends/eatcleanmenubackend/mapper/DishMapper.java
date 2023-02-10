package com.happy3friends.eatcleanmenubackend.mapper;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;
import com.happy3friends.eatcleanmenubackend.entity.DishEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DishMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DishDTO convertEntityToDTO(DishEntity dishEntity) {
        return Objects.isNull(dishEntity) ? null : modelMapper.map(dishEntity, DishDTO.class);
    }

    public DishEntity convertDTOToEntity(DishDTO dishDTO) {
        return Objects.isNull(dishDTO) ? null : modelMapper.map(dishDTO, DishEntity.class);
    }
}
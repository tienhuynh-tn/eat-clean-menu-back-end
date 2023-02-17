package com.happy3friends.eatcleanmenubackend.mapper;

import com.happy3friends.eatcleanmenubackend.dto.MenuDishDTO;
import com.happy3friends.eatcleanmenubackend.entity.MenuDishEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MenuDishMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MenuDishDTO convertEntityToDTO(MenuDishEntity menuDishEntity) {
        return Objects.isNull(menuDishEntity) ? null : modelMapper.map(menuDishEntity, MenuDishDTO.class);
    }

    public MenuDishEntity convertDTOToEntity(MenuDishDTO menuDishDTO) {
        return Objects.isNull(menuDishDTO) ? null : modelMapper.map(menuDishDTO, MenuDishEntity.class);
    }
}

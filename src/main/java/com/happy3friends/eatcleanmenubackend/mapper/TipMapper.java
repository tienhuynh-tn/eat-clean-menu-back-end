package com.happy3friends.eatcleanmenubackend.mapper;

import com.happy3friends.eatcleanmenubackend.dto.TipDTO;
import com.happy3friends.eatcleanmenubackend.entity.TipEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TipMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TipDTO convertEntityToDTO(TipEntity tipEntity) {
        return Objects.isNull(tipEntity) ? null : modelMapper.map(tipEntity, TipDTO.class);
    }

    public TipEntity convertDTOToEntity(TipDTO tipDTO) {
        return Objects.isNull(tipDTO) ? null : modelMapper.map(tipDTO, TipEntity.class);
    }
}

package com.happy3friends.eatcleanmenubackend.mapper;

import com.happy3friends.eatcleanmenubackend.dto.UserDietaryInfoDTO;
import com.happy3friends.eatcleanmenubackend.entity.UserDietaryInfoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDietaryInfoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDietaryInfoDTO convertEntityToDTO(UserDietaryInfoEntity userDietaryInfoEntity) {
        return Objects.isNull(userDietaryInfoEntity) ? null : modelMapper.map(userDietaryInfoEntity, UserDietaryInfoDTO.class);
    }

    public UserDietaryInfoEntity convertDTOToEntity(UserDietaryInfoDTO userDietaryInfoDTO) {
        return Objects.isNull(userDietaryInfoDTO) ? null : modelMapper.map(userDietaryInfoDTO, UserDietaryInfoEntity.class);
    }
}

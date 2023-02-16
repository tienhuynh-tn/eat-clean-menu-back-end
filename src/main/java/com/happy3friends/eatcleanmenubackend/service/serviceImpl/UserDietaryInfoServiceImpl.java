package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.dto.UserDietaryInfoDTO;
import com.happy3friends.eatcleanmenubackend.entity.UserDietaryInfoEntity;
import com.happy3friends.eatcleanmenubackend.mapper.UserDietaryInfoMapper;
import com.happy3friends.eatcleanmenubackend.repository.UserDietaryInfoRepository;
import com.happy3friends.eatcleanmenubackend.service.UserDietaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDietaryInfoServiceImpl implements UserDietaryInfoService {
    @Autowired
    private UserDietaryInfoRepository userDietaryInfoRepository;

    @Autowired
    private UserDietaryInfoMapper mapper;

    @Override
    public void createUserDietaryInfoByUserId(UserDietaryInfoDTO userDietaryInfoDTO, int userId) {

        userDietaryInfoDTO.setUserId(userId);

        UserDietaryInfoEntity userDietaryInfoEntity = mapper.convertDTOToEntity(userDietaryInfoDTO);

        userDietaryInfoRepository.save(userDietaryInfoEntity);
    }
}

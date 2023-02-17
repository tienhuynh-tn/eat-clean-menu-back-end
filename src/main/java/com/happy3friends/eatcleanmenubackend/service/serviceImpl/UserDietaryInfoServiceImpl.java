package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.dto.UserDietaryInfoDTO;
import com.happy3friends.eatcleanmenubackend.entity.UserDietaryInfoEntity;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
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
        UserDietaryInfoEntity existed = userDietaryInfoRepository.findByUserId(userId);
        double calories = calculateCaloriesConsumedByBmrAndActivityRateAndDietTarget(
                userDietaryInfoDTO.getBmr(),
                userDietaryInfoDTO.getActivityRate(),
                userDietaryInfoDTO.getDietTarget()
        );

        if (existed != null) {
            userDietaryInfoDTO.setId(existed.getId());
            userDietaryInfoDTO.setUserId(userId);
            userDietaryInfoDTO.setCaloriesConsumed(calories);
            UserDietaryInfoEntity newInfo = mapper.convertDTOToEntity(userDietaryInfoDTO);
            userDietaryInfoRepository.save(newInfo);
            return;
        }

        userDietaryInfoDTO.setUserId(userId);
        userDietaryInfoDTO.setCaloriesConsumed(calories);
        UserDietaryInfoEntity userDietaryInfoEntity = mapper.convertDTOToEntity(userDietaryInfoDTO);
        userDietaryInfoRepository.save(userDietaryInfoEntity);
    }

    @Override
    public double calculateCaloriesConsumedByBmrAndActivityRateAndDietTarget(double bmr, String activityRate, String dietTarget) {
        double calories = 0;
        switch (activityRate) {
            case "Không tập luyện":
                calories = bmr * 1.2;
                break;
            case "Ít tập luyện":
                calories = bmr * 1.375;
                break;
            case "Luyện tập vừa":
                calories = bmr * 1.55;
                break;
            case "Luyện tập nhiều":
                calories = bmr * 1.725;
                break;
            case "Luyện tập cường độ cao":
                calories = bmr * 1.9;
                break;
            default:
                throw new NotFoundException("Invalid activity rate!");
        }

        switch (dietTarget) {
            case "Giảm cân":
                calories -= 500;
                break;
            case "Tăng cân":
                calories += 500;
                break;
            case "Giữ nguyên cân nặng":
                break;
            default:
                throw new NotFoundException("Invalid diet target!");
        }

        return calories;
    }
}

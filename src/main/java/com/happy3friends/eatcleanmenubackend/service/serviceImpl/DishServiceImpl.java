package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;
import com.happy3friends.eatcleanmenubackend.entity.DishEntity;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
import com.happy3friends.eatcleanmenubackend.mapper.DishMapper;
import com.happy3friends.eatcleanmenubackend.repository.DishRepository;
import com.happy3friends.eatcleanmenubackend.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishMapper dishMapper;

    @Override
    public DishDTO findById(int dishId) {
        Optional<DishEntity> dishEntity = dishRepository.findById(dishId);

        return dishEntity.map(entity -> dishMapper.convertEntityToDTO(entity)).orElseThrow(() -> new NotFoundException("Dish with Id " + dishId + " Not Found!"));
    }
}
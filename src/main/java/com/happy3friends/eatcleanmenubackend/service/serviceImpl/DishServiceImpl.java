package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.dto.DishDTO;
import com.happy3friends.eatcleanmenubackend.entity.DishEntity;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
import com.happy3friends.eatcleanmenubackend.mapper.DishMapper;
import com.happy3friends.eatcleanmenubackend.repository.DishRepository;
import com.happy3friends.eatcleanmenubackend.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<DishDTO> findAll() {
        List<DishEntity> dishEntities = dishRepository.findAll();

        if (dishEntities.isEmpty())
            throw new NotFoundException("List of dishes is not found!");

        return dishEntities.stream()
                .map(e -> dishMapper.convertEntityToDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public DishDTO findById(int dishId) {
        Optional<DishEntity> dishEntity = dishRepository.findById(dishId);

        return dishEntity.map(entity -> dishMapper.convertEntityToDTO(entity)).orElseThrow(() -> new NotFoundException("Dish with Id " + dishId + " Not Found!"));
    }
}
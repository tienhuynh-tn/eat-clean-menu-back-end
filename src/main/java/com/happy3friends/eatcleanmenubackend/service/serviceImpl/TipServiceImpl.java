package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.dto.TipDTO;
import com.happy3friends.eatcleanmenubackend.entity.TipEntity;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
import com.happy3friends.eatcleanmenubackend.mapper.TipMapper;
import com.happy3friends.eatcleanmenubackend.repository.TipRepository;
import com.happy3friends.eatcleanmenubackend.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipServiceImpl implements TipService {

    @Autowired
    private TipRepository tipRepository;

    @Autowired
    private TipMapper tipMapper;

    @Override
    public List<TipDTO> findAll() {
        List<TipEntity> tipEntities = tipRepository.findAll();

        if (tipEntities.isEmpty())
            throw new NotFoundException("List of tips is not found!");

        return tipEntities.stream()
                .map(e -> tipMapper.convertEntityToDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public TipDTO findById(int tipId) {
        Optional<TipEntity> tipEntity = tipRepository.findById(tipId);

        return tipEntity.map(entity -> tipMapper.convertEntityToDTO(entity)).orElseThrow(() -> new NotFoundException("Tip with Id " + tipId + " Not Found!"));
    }
}

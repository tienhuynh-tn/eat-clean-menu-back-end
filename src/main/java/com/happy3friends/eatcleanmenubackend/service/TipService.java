package com.happy3friends.eatcleanmenubackend.service;

import com.happy3friends.eatcleanmenubackend.dto.TipDTO;

import java.util.List;

public interface TipService {
    List<TipDTO> findAll();
    TipDTO findById(int tipId);
}

package com.happy3friends.eatcleanmenubackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    private int id;
    private String name;
    private double calories;
    private String image;
}
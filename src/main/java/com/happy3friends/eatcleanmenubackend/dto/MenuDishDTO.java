package com.happy3friends.eatcleanmenubackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDishDTO {
    private int id;
    private int dishId;
    private Date mealDate;
    private String mealTime;
    private String status;
    private DishDTO dishByDishId;
}

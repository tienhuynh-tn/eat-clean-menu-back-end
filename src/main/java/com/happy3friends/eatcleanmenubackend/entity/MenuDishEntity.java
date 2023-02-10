package com.happy3friends.eatcleanmenubackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "Menu_Dish", schema = "dbo", catalog = "ECM")
public class MenuDishEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "MenuId", nullable = false)
    private int menuId;
    @Basic
    @Column(name = "DishId", nullable = false)
    private int dishId;
    @Basic
    @Column(name = "MealDay", nullable = false)
    private Date mealDay;
    @Basic
    @Column(name = "MealTime", nullable = false, length = 10)
    private String mealTime;
    @Basic
    @Column(name = "Status", nullable = true, length = 20)
    private String status;
}
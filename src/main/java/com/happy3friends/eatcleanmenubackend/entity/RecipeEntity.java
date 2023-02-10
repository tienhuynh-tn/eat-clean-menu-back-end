package com.happy3friends.eatcleanmenubackend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Recipe", schema = "dbo", catalog = "ECM")
public class RecipeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "DishId", nullable = false)
    private int dishId;
    @Basic
    @Column(name = "StepNo", nullable = false)
    private int stepNo;
    @Basic
    @Column(name = "Description", nullable = false, length = -1)
    private String description;
}
package com.happy3friends.eatcleanmenubackend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Dish", schema = "dbo", catalog = "ECM")
public class DishEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "Calories", nullable = false, precision = 0)
    private double calories;
    @Basic
    @Column(name = "Image", nullable = false, length = -1)
    private String image;
}
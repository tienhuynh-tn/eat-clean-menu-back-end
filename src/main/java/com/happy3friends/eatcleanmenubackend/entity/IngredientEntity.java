package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Ingredient", schema = "dbo", catalog = "ecm")
public class IngredientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "DishId", nullable = false)
    private int dishId;
    @Basic
    @Column(name = "Name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "Quantity", nullable = false, length = 20)
    private String quantity;
    @Basic
    @Column(name = "Type", nullable = false, length = 20)
    private String type;

}

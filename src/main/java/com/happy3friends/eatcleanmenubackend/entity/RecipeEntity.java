package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Recipe", schema = "dbo", catalog = "ecm")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DishId", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    private DishEntity dishByDishId;
}

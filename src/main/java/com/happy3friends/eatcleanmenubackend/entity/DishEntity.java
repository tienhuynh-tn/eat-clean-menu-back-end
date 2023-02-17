package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Dish", schema = "dbo", catalog = "ecm")
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
    @Basic
    @Column(name = "Type", nullable = true, length = 10)
    private String type;
    @OneToMany(mappedBy = "dishByDishId")
    private Collection<MenuDishEntity> menuDishesById;
    @OneToMany(mappedBy = "dishByDishId")
    private Collection<IngredientEntity> ingredientsById;
    @OneToMany(mappedBy = "dishByDishId")
    private Collection<RecipeEntity> recipesById;
}

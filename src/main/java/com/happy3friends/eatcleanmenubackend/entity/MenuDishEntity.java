package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "Menu_Dish", schema = "dbo", catalog = "ECM")
public class MenuDishEntity {
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
    @Column(name = "MealDate", nullable = false)
    private Date mealDate;
    @Basic
    @Column(name = "MealTime", nullable = false, length = 10)
    private String mealTime;
    @Basic
    @Column(name = "Status", nullable = true, length = 20)
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MenuId", referencedColumnName = "Id", insertable = false, updatable = false)
    private MenuEntity menuByMenuId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DishId", referencedColumnName = "Id", insertable = false, updatable = false)
    private DishEntity dishByDishId;

}

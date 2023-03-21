package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Ingredient", schema = "dbo", catalog = "ECM")
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
    @Column(name = "Quantity", nullable = true, length = 20)
    private String quantity;
    @Basic
    @Column(name = "Type", nullable = false, length = 20)
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DishId", referencedColumnName = "Id", insertable = false, updatable = false)
    private DishEntity dishByDishId;

}

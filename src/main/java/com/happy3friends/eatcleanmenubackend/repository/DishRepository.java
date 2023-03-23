package com.happy3friends.eatcleanmenubackend.repository;

import com.happy3friends.eatcleanmenubackend.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Integer> {
    @Query(value = "select top 1 * " +
            "        from Dish " +
            "        where Calories between :minCalo and :maxCalo " +
            "        order by newid() ", nativeQuery = true)
    DishEntity randomDishByCaloriesBetween(@Param("minCalo") double minCalo, @Param("maxCalo") double maxCalo);

    @Query(value = "select d " +
            "from DishEntity d " +
            "join fetch d.ingredientsById " +
            "where d.id = :dishId")
    DishEntity findDishesWithIngredientByDishId(@Param("dishId") int dishId);

    @Query(value = "select d " +
            "from DishEntity d " +
            "join fetch d.recipesById " +
            "where d.id = :dishId")
    DishEntity findDishesWithRecipeByDishId(@Param("dishId") int dishId);
}
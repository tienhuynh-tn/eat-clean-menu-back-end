package com.happy3friends.eatcleanmenubackend.repository;

import com.happy3friends.eatcleanmenubackend.entity.MenuDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface MenuDishRepository extends JpaRepository<MenuDishEntity, Integer>, CrudRepository<MenuDishEntity, Integer> {
    @Query(value = "SELECT * FROM Menu_Dish m JOIN Dish d on d.Id = m.DishId WHERE MealDate = :mealDate AND MenuId = :menuId", nativeQuery = true)
    List<MenuDishEntity> findByMealDateAndMenuId(@Param("mealDate") Date mealDate, @Param("menuId") int menuId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Menu_Dish " +
            "SET " +
            "    Status = 'Hoàn thành' " +
            "WHERE " +
            "    Id = :id ", nativeQuery = true)
    void updateStatusById(@Param("id") int id);
}

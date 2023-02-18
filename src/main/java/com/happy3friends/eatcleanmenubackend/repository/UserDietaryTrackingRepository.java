package com.happy3friends.eatcleanmenubackend.repository;

import com.happy3friends.eatcleanmenubackend.dto.CustomUserDietaryTrackingDTO;
import com.happy3friends.eatcleanmenubackend.entity.UserDietaryTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserDietaryTrackingRepository extends JpaRepository<UserDietaryTrackingEntity, Integer> {
    List<UserDietaryTrackingEntity> findByUserId(int userId);

    @Query(value = "select md.MealTime, md.Status, d.Calories " +
            "from Menu_Dish md join Menu m " +
            "    on md.MenuId = m.Id " +
            "    join dish d " +
            "    on md.DishId = d.Id " +
            "where m.UserId = :userId " +
            "and DATEPART(dd, md.MealDate) = DATEPART(dd, :mealDate) " +
            "and DATEPART(MM, md.MealDate) = DATEPART(MM, :mealDate) " +
            "and DATEPART(yyyy, md.MealDate) = DATEPART(yyyy, :mealDate)", nativeQuery = true)
    List<CustomUserDietaryTrackingDTO> trackingCaloriesByDate(@Param("userId") int userId, @Param("mealDate") Timestamp date);
}

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

    @Query(value = "select md.MealDate, SUM(CASE WHEN (md.Status = 'Hoàn thành') THEN Calories else 0 END) as Calories\n" +
            "from Menu_Dish md join menu m\n" +
            "on md.MenuId = m.Id and m.UserId = :userId\n" +
            "join Dish D on md.DishId = D.Id\n" +
            "where MealDate\n" +
            "    between DATEADD(DAY, 2 - DATEPART(WEEKDAY, :now), CAST(:now AS DATE))\n" +
            "    and DATEADD(DAY, 8 - DATEPART(WEEKDAY, :now), CAST(:now AS DATE))\n" +
            "group by md.MealDate", nativeQuery = true)
    List<CustomUserDietaryTrackingDTO> trackingCaloriesByWeek(@Param("userId") int userId, @Param("now") Timestamp date);

    @Query(value = "select SUM(CASE WHEN (md.Status = 'Hoàn thành') THEN Calories END) as Calories\n" +
            "from Menu_Dish md join menu m\n" +
            "on md.MenuId = m.Id and m.UserId = :userId\n" +
            "join Dish D on md.DishId = D.Id\n" +
            "where datepart(mm, md.MealDate) = datepart(mm, :now)", nativeQuery = true)
    CustomUserDietaryTrackingDTO trackingCaloriesByMonth(@Param("userId") int userId, @Param("now") Timestamp date);
}

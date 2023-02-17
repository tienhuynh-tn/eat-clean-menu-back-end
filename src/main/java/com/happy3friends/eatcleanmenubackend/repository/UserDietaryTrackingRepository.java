package com.happy3friends.eatcleanmenubackend.repository;

import com.happy3friends.eatcleanmenubackend.entity.UserDietaryTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDietaryTrackingRepository extends JpaRepository<UserDietaryTrackingEntity, Integer> {
    List<UserDietaryTrackingEntity> findByUserId(int userId);
}

package com.happy3friends.eatcleanmenubackend.repository;

import com.happy3friends.eatcleanmenubackend.entity.UserDietaryInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDietaryInfoRepository extends JpaRepository<UserDietaryInfoEntity, Integer>, CrudRepository<UserDietaryInfoEntity, Integer> {
    UserDietaryInfoEntity findByUserId(int userId);
}

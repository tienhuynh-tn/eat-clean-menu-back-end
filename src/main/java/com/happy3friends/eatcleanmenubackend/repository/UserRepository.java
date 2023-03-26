package com.happy3friends.eatcleanmenubackend.repository;

import com.happy3friends.eatcleanmenubackend.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    @Query(value = "SELECT * FROM [Users] WHERE Gmail = :email", nativeQuery = true)
    Optional<UsersEntity> findByGmail(String email);
}

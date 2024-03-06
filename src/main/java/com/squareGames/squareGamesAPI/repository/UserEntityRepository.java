package com.squareGames.squareGamesAPI.repository;

import com.squareGames.squareGamesAPI.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity , Integer> {

    UserEntity findByUsername(String username);
}

package com.squareGames.squareGamesAPI.repository;

import com.squareGames.squareGamesAPI.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}

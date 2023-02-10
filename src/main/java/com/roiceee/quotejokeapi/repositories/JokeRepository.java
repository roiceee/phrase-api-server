package com.roiceee.quotejokeapi.repositories;

import com.roiceee.quotejokeapi.models.JokeModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JokeRepository extends CrudRepository<JokeModel, Long> {

    @Query(value =
            "SELECT * FROM jokes " +
            "ORDER BY RAND() LIMIT  1",
            nativeQuery = true)
    JokeModel getRandomJoke();
}

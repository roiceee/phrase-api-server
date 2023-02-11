package com.roiceee.quotejokeapi.repositories;

import com.roiceee.quotejokeapi.models.JokeModel;

import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.models.QuoteModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JokeRepository extends CrudRepository<JokeModel, Long> {

    @Query(value =
            "SELECT * FROM jokes " +
            "ORDER BY RAND() LIMIT  1",
            nativeQuery = true)
    JokeModel getRandomJoke();

    @Query(value = "SELECT * FROM jokes " +
            "ORDER BY RAND() LIMIT  :quantity",
            nativeQuery = true)
    List<JokeModel> getRandomJokeList(int quantity);
}

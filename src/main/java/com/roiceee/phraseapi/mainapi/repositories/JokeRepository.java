package com.roiceee.phraseapi.mainapi.repositories;

import com.roiceee.phraseapi.mainapi.models.JokeModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JokeRepository extends PagingAndSortingRepository<JokeModel, Long> {

    @Query(value =
            "SELECT * FROM jokes " +
            "ORDER BY RAND() LIMIT  1",
            nativeQuery = true)
    JokeModel getRandomJoke();

    @Query(value = "SELECT * FROM jokes " +
            "ORDER BY RAND() LIMIT  :quantity",
            nativeQuery = true)
    List<JokeModel> getRandomJokeList(int quantity);

    @Query(value = "SELECT * FROM jokes " +
            "WHERE PHRASE REGEXP :query " +
            "ORDER BY RAND() LIMIT :quantity",
            nativeQuery = true)
    List<JokeModel> getRandomJokeListWithQuery(int quantity, String query);

    Page<JokeModel> findAllByPhraseIsContaining(String phrase, Pageable pageable);
}
package com.roiceee.phraseapi.resourceapi.repository;

import com.roiceee.phraseapi.resourceapi.model.JokeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JokeRepository extends JpaRepository<JokeModel, Long> {

    @Query(value =
            "SELECT * FROM public.jokes " +
            "ORDER BY random() LIMIT  1",
            nativeQuery = true)
    JokeModel getRandomJoke();

    @Query(value = "SELECT * FROM public.jokes " +
            "ORDER BY random() LIMIT  :quantity",
            nativeQuery = true)
    List<JokeModel> getRandomJokeList(int quantity);

    @Query(value = "SELECT * FROM public.jokes " +
            "WHERE lower(phrase) LIKE '%'||lower(:query)||'%' " +
            "ORDER BY random() LIMIT :quantity",
            nativeQuery = true)
    List<JokeModel> getRandomJokeListWithQuery(int quantity, String query);

    Page<JokeModel> findAllByPhraseIsContainingIgnoreCaseOrderByTimestampAsc(String phrase, Pageable pageable);

    Page<JokeModel> findAllByPhraseIsContainingIgnoreCaseOrderByTimestampDesc(String phrase, Pageable pageable);
    Page<JokeModel> findAllByAuthorIsContainingIgnoreCaseOrderByTimestampAsc(String phrase, Pageable pageable);

    Page<JokeModel> findAllByAuthorIsContainingIgnoreCaseOrderByTimestampDesc(String phrase, Pageable pageable);

    Page<JokeModel> findAllByPhraseIsContaining(String phrase, Pageable pageable);

    void deleteByPhraseManagementID(Long id);


}

package com.roiceee.phraseapi.phrasemanagement.repositories;

import com.roiceee.phraseapi.phrasemanagement.models.PhrasePostObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PhraseManagementRepository extends JpaRepository<PhrasePostObject, Long> {
    boolean existsPhrasePostObjectByPhrase(String phrase);

    boolean existsPhrasePostObjectByIdAndUserId(Long id, String userId);

    void deletePhrasePostObjectByIdAndUserId(Long id, String userId);

    long countPhrasePostObjectByUserId(String userId);
    @Modifying
    @Query(value = "UPDATE phrase_management " +
            "SET author = :author, phrase = :phrase " +
            "WHERE id = :id AND user_id = :userId", nativeQuery = true)
    void updatePhrasePostObject(String author, String phrase, String userId, long id);

    List<PhrasePostObject> findAllByUserId(String userId);
}

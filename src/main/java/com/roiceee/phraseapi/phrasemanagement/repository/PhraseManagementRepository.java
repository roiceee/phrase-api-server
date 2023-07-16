package com.roiceee.phraseapi.phrasemanagement.repository;

import com.roiceee.phraseapi.phrasemanagement.model.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PhraseManagementRepository extends JpaRepository<PhrasePostObject, Long> {
    boolean existsPhrasePostObjectByPhrase(String phrase);

    boolean existsPhrasePostObjectByIdAndUserId(Long id, String userId);

    void deletePhrasePostObjectByIdAndUserId(Long id, String userId);

    long countPhrasePostObjectByUserId(String userId);

    long countByStatus(Status status);

    boolean existsPhrasePostObjectByPhraseAndId(String phrase, Long id);

    Page<PhrasePostObject> findAllByStatus(Pageable pageable, Status status);

    List<PhrasePostObject> findAllByUserIdOrderByDateSubmittedAsc(String userId);

    List<PhrasePostObject> findAllByUserIdOrderByPhraseAsc(String userId);
    Optional<PhrasePostObject> findByIdAndUserId(Long id, String userId);
}

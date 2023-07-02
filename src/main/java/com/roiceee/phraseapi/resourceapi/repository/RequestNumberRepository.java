package com.roiceee.phraseapi.resourceapi.repository;

import com.roiceee.phraseapi.resourceapi.model.RequestCountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RequestNumberRepository extends JpaRepository<RequestCountModel, Long> {
    @Query(value = "SELECT SUM(count) FROM request_counter", nativeQuery = true)
    long getCountSum();
    Optional<RequestCountModel> findByApiKey(UUID apiKey);
}

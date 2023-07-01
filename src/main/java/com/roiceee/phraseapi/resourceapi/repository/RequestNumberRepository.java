package com.roiceee.phraseapi.resourceapi.repository;

import com.roiceee.phraseapi.resourceapi.model.RequestCountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RequestNumberRepository extends JpaRepository<RequestCountModel, Long> {


    Optional<RequestCountModel> findByApiKey(UUID apiKey);
}

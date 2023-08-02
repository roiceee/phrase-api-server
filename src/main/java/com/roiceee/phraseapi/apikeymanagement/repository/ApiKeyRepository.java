package com.roiceee.phraseapi.apikeymanagement.repository;

import com.roiceee.phraseapi.apikeymanagement.model.UserApiKeyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<UserApiKeyModel, String> {

    UserApiKeyModel findByApiKey(String apiKey);

    boolean existsByApiKey(String apiKey);
}

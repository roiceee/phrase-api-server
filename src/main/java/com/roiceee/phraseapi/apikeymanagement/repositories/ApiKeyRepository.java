package com.roiceee.phraseapi.apikeymanagement.repositories;

import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ApiKeyRepository extends CrudRepository <UserApiKeyModel, String> {

    @Modifying
    @Query(value = "INSERT INTO public.api_keys (id, api_key) VALUES (:id, :apiKey);", nativeQuery = true)
    void createApiKey(String id, String apiKey);

    boolean existsByApiKey(String apiKey);
}

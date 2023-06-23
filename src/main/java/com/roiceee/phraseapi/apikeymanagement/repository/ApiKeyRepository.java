package com.roiceee.phraseapi.apikeymanagement.repository;

import com.roiceee.phraseapi.apikeymanagement.model.UserApiKeyModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ApiKeyRepository extends CrudRepository <UserApiKeyModel, String> {

    @Modifying
    @Query(value = "INSERT INTO public.api_keys (id, api_key) VALUES (:id, :apiKey);", nativeQuery = true)
    void createApiKey(String id, String apiKey);

    @Query(value = "SELECT * FROM public.api_keys;", nativeQuery = true)
    List<UserApiKeyModel> getAll();

    boolean existsByApiKey(String apiKey);
}

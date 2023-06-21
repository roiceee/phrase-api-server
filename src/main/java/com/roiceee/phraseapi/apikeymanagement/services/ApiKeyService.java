package com.roiceee.phraseapi.apikeymanagement.services;

import com.roiceee.phraseapi.apikeymanagement.exceptions.ApiKeyNotFoundException;
import com.roiceee.phraseapi.apikeymanagement.exceptions.UserHasApiKeyAlreadyException;
import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.repositories.ApiKeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;



    public UserApiKeyModel createNewApiKey(String id) {
        if (userAlreadyHasKey(id)) {
            throw new UserHasApiKeyAlreadyException();
        }
        String uuid = UUID.randomUUID().toString();
        apiKeyRepository.createApiKey(id, uuid);
        UserApiKeyModel model = new UserApiKeyModel();
        model.setApiKey(uuid);
        return model;
    }

    public UserApiKeyModel getApiKey(String id) {
        Optional<UserApiKeyModel> key = apiKeyRepository.findById(id);
        if (key.isEmpty()) {
            throw new ApiKeyNotFoundException();
        }
        return key.get();
    }

    public Optional<UserApiKeyModel> deleteApiKey(String id) {
        if (!userAlreadyHasKey(id)) {
            throw new ApiKeyNotFoundException();
        }
        Optional<UserApiKeyModel> keyModel = apiKeyRepository.findById(id);
        apiKeyRepository.deleteById(id);
        return keyModel;
    }

    public boolean userAlreadyHasKey(String id) {
        return apiKeyRepository.existsById(id);
    }

    public void checkIfApiKeyExists(String apiKey) {
        if (!apiKeyRepository.existsByApiKey(apiKey)) {
            throw new ApiKeyNotFoundException();
        }
    }

}

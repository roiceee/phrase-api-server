package com.roiceee.phraseapi.apikeymanagement.services;

import com.roiceee.phraseapi.apikeymanagement.exceptions.UserHasApiKeyAlreadyException;
import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.repositories.ApiKeyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ApiKeyService {
    ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public String createNewApiKey(String id) {
        if (userAlreadyHasKey(id)) {
            throw new UserHasApiKeyAlreadyException();
        }
        String uuid = UUID.randomUUID().toString();
        apiKeyRepository.createApiKey(id, uuid);
        return uuid;
    }

    public Optional<UserApiKeyModel> getApiKey(String id) {
        return apiKeyRepository.findById(id);
    }

    public void deleteApiKey(String id) {
        apiKeyRepository.deleteById(id);
    }

    public boolean apiKeyExists(String apiKey) {
        return apiKeyRepository.existsByApiKey(apiKey);
    }

    public boolean userAlreadyHasKey(String id) {
        return apiKeyRepository.existsById(id);
    }
}

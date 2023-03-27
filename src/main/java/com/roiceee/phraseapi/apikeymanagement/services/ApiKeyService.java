package com.roiceee.phraseapi.apikeymanagement.services;

import com.roiceee.phraseapi.apikeymanagement.exceptions.ApiKeyNotFoundException;
import com.roiceee.phraseapi.apikeymanagement.exceptions.UserHasApiKeyAlreadyException;
import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.repositories.ApiKeyRepository;
import com.roiceee.phraseapi.util.RateLimiterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ApiKeyService {
    ApiKeyRepository apiKeyRepository;
    RateLimiterService rateLimiterService;

    public ApiKeyService(ApiKeyRepository apiKeyRepository, RateLimiterService rateLimiterService) {
        this.apiKeyRepository = apiKeyRepository;
        this.rateLimiterService = rateLimiterService;
    }

    public UserApiKeyModel createNewApiKey(String id) {
        if (userAlreadyHasKey(id)) {
            throw new UserHasApiKeyAlreadyException();
        }
        String uuid = UUID.randomUUID().toString();
        apiKeyRepository.createApiKey(id, uuid);
        UserApiKeyModel model = new UserApiKeyModel();
        rateLimiterService.addExistingKeyIfAbsentInCache(uuid);
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

    public List<UserApiKeyModel> getAllUserApiKeyModel() {
        return apiKeyRepository.getAll();
    }

    public void deleteApiKey(String id) {
        if (!userAlreadyHasKey(id)) {
            throw new ApiKeyNotFoundException();
        }
        Optional<UserApiKeyModel> keyModel = apiKeyRepository.findById(id);
        keyModel.ifPresent(userApiKeyModel -> rateLimiterService.deleteFromCache(userApiKeyModel.getApiKey()));
        apiKeyRepository.deleteById(id);
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

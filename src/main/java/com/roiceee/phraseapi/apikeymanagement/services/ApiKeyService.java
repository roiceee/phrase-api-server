package com.roiceee.phraseapi.apikeymanagement.services;

import com.roiceee.phraseapi.apikeymanagement.exceptions.ApiKeyNotFoundException;
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

    public void deleteApiKey(String id) {
        if (!userAlreadyHasKey(id)) {
            throw new ApiKeyNotFoundException();
        }
        apiKeyRepository.deleteById(id);
    }

    public boolean userAlreadyHasKey(String id) {
        return apiKeyRepository.existsById(id);
    }
}

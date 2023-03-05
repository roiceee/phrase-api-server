package com.roiceee.phraseapi.apikeymanagement.services;
import com.roiceee.phraseapi.apikeymanagement.repositories.ApiKeyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiKeyService {
    ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public void createNewApiKey(String id) {

    }

    public String getApiKey(String id) {
        return "";
    }

    public void setNewApiKey(String id) {

    }

    public boolean checkIFApiKeyExists(String apiKey) {
        return true;
    }
}

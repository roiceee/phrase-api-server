package com.roiceee.phraseapi.apikeymanagement.service;

import com.roiceee.phraseapi.apikeymanagement.dto.UserApiKeyDTO;
import com.roiceee.phraseapi.apikeymanagement.exception.ApiKeyNotFoundException;
import com.roiceee.phraseapi.apikeymanagement.exception.UserHasApiKeyAlreadyException;
import com.roiceee.phraseapi.apikeymanagement.model.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.repository.ApiKeyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;

    private final ModelMapper modelMapper;

    public UserApiKeyDTO createNewApiKey(String id) {
        if (userAlreadyHasKey(id)) {
            throw new UserHasApiKeyAlreadyException();
        }
        String uuid = UUID.randomUUID().toString();
        apiKeyRepository.createApiKey(id, uuid);
        UserApiKeyModel model = new UserApiKeyModel();
        model.setApiKey(uuid);
        return convertToDTO(model);
    }



    public UserApiKeyDTO getApiKey(String id) {
        Optional<UserApiKeyModel> key = apiKeyRepository.findById(id);
        if (key.isEmpty()) {
            throw new ApiKeyNotFoundException();
        }
        return convertToDTO(key.get());
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

    public void checkIfApiKeyExists(String apiKey) {
        if (!apiKeyRepository.existsByApiKey(apiKey)) {
            throw new ApiKeyNotFoundException();
        }
    }

    private UserApiKeyDTO convertToDTO(UserApiKeyModel model) {
        return modelMapper.map(model, UserApiKeyDTO.class);
    }

}

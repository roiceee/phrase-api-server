package com.roiceee.phraseapi.resourceapi.service;

import com.roiceee.phraseapi.apikeymanagement.repository.ApiKeyRepository;
import com.roiceee.phraseapi.resourceapi.model.RequestCountModel;
import com.roiceee.phraseapi.resourceapi.repository.RequestNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RequestCountService {
    private final RequestNumberRepository requestNumberRepository;
    private final ApiKeyRepository apiKeyRepository;

    //if the api key is present in the database, increment the count by 1. If not, create a new entry with count 1.
    public void addCount(String apiKey) {

        Optional<RequestCountModel> modelOptional = requestNumberRepository.findByApiKey(UUID.fromString(apiKey));
        if (modelOptional.isPresent()) {
            RequestCountModel model = modelOptional.get();
            model.setCount(model.getCount() + 1);
            requestNumberRepository.save(model);
            return;
        }

        RequestCountModel model = new RequestCountModel(UUID.fromString(apiKey), 1L,
                apiKeyRepository.findByApiKey(apiKey).getID());

        requestNumberRepository.save(model);
    }
}

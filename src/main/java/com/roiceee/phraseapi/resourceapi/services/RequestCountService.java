package com.roiceee.phraseapi.resourceapi.services;

import com.roiceee.phraseapi.resourceapi.repositories.RequestNumberRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestCountService {
    private final RequestNumberRepository requestNumberRepository;

    public RequestCountService(RequestNumberRepository requestNumberRepository) {
        this.requestNumberRepository = requestNumberRepository;
    }

    public void addCount(String apiKey) {
        requestNumberRepository.addRequestCount(apiKey);
    }
}

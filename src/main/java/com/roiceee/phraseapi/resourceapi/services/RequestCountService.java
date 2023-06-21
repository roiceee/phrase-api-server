package com.roiceee.phraseapi.resourceapi.services;

import com.roiceee.phraseapi.resourceapi.repositories.RequestNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class RequestCountService {
    private final RequestNumberRepository requestNumberRepository;

    public void addCount(String apiKey) {
        requestNumberRepository.addRequestCount(apiKey);
    }
}

package com.roiceee.phraseapi.resourceapi.service;

import com.roiceee.phraseapi.resourceapi.repository.RequestNumberRepository;
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

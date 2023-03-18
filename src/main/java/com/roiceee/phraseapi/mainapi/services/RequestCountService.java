package com.roiceee.phraseapi.mainapi.services;

import com.roiceee.phraseapi.mainapi.repositories.RequestNumberRepository;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
=======

@Service
>>>>>>> origin/main
public class RequestCountService {
    RequestNumberRepository requestNumberRepository;

    public RequestCountService(RequestNumberRepository requestNumberRepository) {
        this.requestNumberRepository = requestNumberRepository;
    }

    public void addCount(String apiKey) {
        requestNumberRepository.addRequestCount(apiKey);
    }
}

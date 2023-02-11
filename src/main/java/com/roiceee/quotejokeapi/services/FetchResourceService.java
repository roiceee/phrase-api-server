package com.roiceee.quotejokeapi.services;

import com.roiceee.quotejokeapi.exceptions.WrongReqParamTypeException;
import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.repositories.JokeRepository;
import com.roiceee.quotejokeapi.repositories.QuoteRepository;
import com.roiceee.quotejokeapi.util.ReqParamTypeValues;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FetchResourceService {
    JokeRepository jokeRepository;
    QuoteRepository quoteRepository;

    public FetchResourceService(JokeRepository jokeRepository, QuoteRepository quoteRepository) {
        this.jokeRepository = jokeRepository;
        this.quoteRepository = quoteRepository;
    }

    public Phrase getRandomPhrase(String type) {
        return switch (type) {
            case ReqParamTypeValues.JOKE -> getRandomJoke();
            case ReqParamTypeValues.QUOTE -> getRandomQuote();
            default -> throw new WrongReqParamTypeException(type);
        };
    }

    private Phrase getRandomJoke() {
        return jokeRepository.getRandomJoke();
    }

    private Phrase getRandomQuote() {
        return quoteRepository.getRandomQuote();
    }

}

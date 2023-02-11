package com.roiceee.quotejokeapi.services;

import com.roiceee.quotejokeapi.exceptions.InvalidParamQuantityValueException;
import com.roiceee.quotejokeapi.exceptions.InvalidParamTypeValueException;
import com.roiceee.quotejokeapi.models.JokeModel;
import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.models.QuoteModel;
import com.roiceee.quotejokeapi.repositories.JokeRepository;
import com.roiceee.quotejokeapi.repositories.QuoteRepository;
import com.roiceee.quotejokeapi.util.ReqParamTypeValues;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJoke();
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuote();
            default -> throw new InvalidParamTypeValueException(type);
        };
    }

    public List<? extends Phrase> getRandomPhraseList(String type, int quantity) {
        if (quantity < ReqParamTypeValues.MINQTY ||
                quantity > ReqParamTypeValues.MAXQTY) {
            throw new InvalidParamQuantityValueException(quantity);
        }

        return switch (type) {
            case ReqParamTypeValues.JOKE ->
                    jokeRepository.getRandomJokeList(quantity);
            case ReqParamTypeValues.QUOTE ->
                    quoteRepository.getRandomQuoteList(quantity);
            default -> throw new InvalidParamTypeValueException(type);
        };
    }


}

package com.roiceee.quotejokeapi;

import com.roiceee.quotejokeapi.exceptions.WrongReqParamTypeException;
import com.roiceee.quotejokeapi.models.JokeModel;
import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.models.QuoteModel;
import com.roiceee.quotejokeapi.repositories.JokeRepository;
import com.roiceee.quotejokeapi.repositories.QuoteRepository;
import com.roiceee.quotejokeapi.services.FetchResourceService;
import com.roiceee.quotejokeapi.util.ReqParamTypeValues;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class FetchResourceServiceUnitTest {

    @Mock
    JokeRepository jokeRepository;

    @Mock
    QuoteRepository quoteRepository;

    @InjectMocks
    FetchResourceService fetchResourceService;

    @Test
    @Description("Check happy flow when 'type' parameter value of 'joke' is input.")
    public void getRandomJokeTestHappyFlow() {
        String type = ReqParamTypeValues.JOKE;
        JokeModel jokeModel = new JokeModel();
        jokeModel.setPhrase("Test Joke");

        Mockito.when(jokeRepository.getRandomJoke()).thenReturn(jokeModel);

        Phrase phrase = fetchResourceService.getRandomPhrase(type);

        Assertions.assertEquals(jokeModel, phrase);
    }

    @Test
    @Description("Check happy flow when 'type' parameter value of 'quote' is input.")
    public void getRandomQuoteTestHappyFlow() {
        String type = ReqParamTypeValues.QUOTE;
        QuoteModel quoteModel = new QuoteModel();
        quoteModel.setPhrase("Test Quote");

        Mockito.when(quoteRepository.getRandomQuote()).thenReturn(quoteModel);

        Phrase phrase = fetchResourceService.getRandomPhrase(type);

        Assertions.assertEquals(quoteModel, phrase);
    }

    @Test
    @Description("Throw exception when wrong type is input.")
    public void getRandomJokeTestWrongType() {
        String type = "wrong type";

        Assertions.assertThrows(WrongReqParamTypeException.class,
                () -> {
                    fetchResourceService.getRandomPhrase(type);
                });
    }

}

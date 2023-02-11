package com.roiceee.quotejokeapi;

import com.roiceee.quotejokeapi.exceptions.InvalidParamQuantityValueException;
import com.roiceee.quotejokeapi.exceptions.InvalidParamTypeValueException;
import com.roiceee.quotejokeapi.models.JokeModel;
import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.models.QuoteModel;
import com.roiceee.quotejokeapi.repositories.JokeRepository;
import com.roiceee.quotejokeapi.repositories.QuoteRepository;
import com.roiceee.quotejokeapi.services.FetchResourceService;
import com.roiceee.quotejokeapi.util.ReqParamQtyValues;
import com.roiceee.quotejokeapi.util.ReqParamTypeValues;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


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
    public void getRandomJokeTest() {
        String type = ReqParamTypeValues.JOKE;
        JokeModel jokeModel = new JokeModel();
        jokeModel.setPhrase("Test Joke");

        Mockito.when(jokeRepository.getRandomJoke()).thenReturn(jokeModel);

        Phrase phrase = fetchResourceService.getRandomPhrase(type);

        Assertions.assertEquals(jokeModel, phrase);
    }

    @Test
    @Description("Check happy flow when 'type' parameter value of 'quote' is input.")
    public void getRandomQuoteTest() {
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

        Assertions.assertThrows(InvalidParamTypeValueException.class,
                () -> {
                    fetchResourceService.getRandomPhrase(type);
                });
    }

    @Test
    public void getRandomJokeListTest() {
        String type = ReqParamTypeValues.JOKE;
        int qty = ReqParamQtyValues.MINQTY;

        JokeModel jokeModel = new JokeModel();
        jokeModel.setPhrase("test joke");
        jokeModel.setID(3);

        List<JokeModel> phrases = new ArrayList<>();
        phrases.add(jokeModel);

        Mockito.when(jokeRepository.getRandomJokeList(qty)).thenReturn(phrases);
        Assertions.assertEquals(fetchResourceService.getRandomPhraseList(type, qty), phrases);
    }

    @Test
    public void getRandomQuoteListTest() {
        String type = ReqParamTypeValues.QUOTE;
        int qty = ReqParamQtyValues.MINQTY;

        QuoteModel quoteModel = new QuoteModel();
        quoteModel.setPhrase("test joke");
        quoteModel.setID(3);
        quoteModel.setAuthor("test author");

        List<QuoteModel> phrases = new ArrayList<>();
        phrases.add(quoteModel);

        Mockito.when(quoteRepository.getRandomQuoteList(qty)).thenReturn(phrases);
        Assertions.assertEquals(fetchResourceService.getRandomPhraseList(type, qty), phrases);
    }

    @Test
    public void getRandomResourceListWrongTypeTest() {
        String type = "wrong type";
        int qty = ReqParamQtyValues.MINQTY;

        Assertions.assertThrows(InvalidParamTypeValueException.class,
                () -> fetchResourceService.getRandomPhraseList(type, qty));
    }

    @Test
    public void getRandomResourceListInvalidQuantityTest() {
        String type = ReqParamTypeValues.JOKE;
        int qty = ReqParamQtyValues.MAXQTY + 1;

        Assertions.assertThrows(InvalidParamQuantityValueException.class,
                () -> fetchResourceService.getRandomPhraseList(type, qty));
    }
}

package com.roiceee.phraseapi;


import com.roiceee.phraseapi.resourceapi.exception.InvalidParamValueException;
import com.roiceee.phraseapi.resourceapi.model.JokeModel;
import com.roiceee.phraseapi.resourceapi.model.Phrase;
import com.roiceee.phraseapi.resourceapi.model.QuoteModel;
import com.roiceee.phraseapi.resourceapi.repository.JokeRepository;
import com.roiceee.phraseapi.resourceapi.repository.QuoteRepository;
import com.roiceee.phraseapi.resourceapi.service.FetchResourceService;
import com.roiceee.phraseapi.resourceapi.util.ReqParamPageValues;
import com.roiceee.phraseapi.resourceapi.util.ReqParamQtyValues;
import com.roiceee.phraseapi.resourceapi.util.ReqParamTypeValues;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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
    public void getRandomJoke() {
        String type = ReqParamTypeValues.JOKE;
        JokeModel jokeModel = new JokeModel();
        jokeModel.setPhrase("Test Joke");

        Mockito.when(jokeRepository.getRandomJoke()).thenReturn(jokeModel);

        Phrase phrase = fetchResourceService.getRandomPhrase(type);

        Assertions.assertEquals(jokeModel, phrase);
    }

    @Test
    @Description("Check happy flow when 'type' parameter value of 'quote' is input.")
    public void getRandomQuote() {
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

        Assertions.assertThrows(InvalidParamValueException.class,
                () -> fetchResourceService.getRandomPhrase(type));
    }

    @Test
    public void getRandomJokeList() {
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
    public void getRandomQuoteList() {
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
    public void getRandomResourceListWrongType() {
        String type = "wrong type";
        int qty = ReqParamQtyValues.MINQTY;

        Assertions.assertThrows(InvalidParamValueException.class,
                () -> fetchResourceService.getRandomPhraseList(type, qty));
    }

    @Test
    public void getRandomResourceListInvalidQuantity() {
        String type = ReqParamTypeValues.JOKE;
        int qty = ReqParamQtyValues.MAXQTY + 1;

        Assertions.assertThrows(InvalidParamValueException.class,
                () -> fetchResourceService.getRandomPhraseList(type, qty));
    }

    @Test
    public void getPhraseListWithQueryPaginationHappyFlow() {
        String type = ReqParamTypeValues.JOKE;
        String query = "sample query";
        int qty = 1;
        int page = ReqParamPageValues.MIN_PAGE;

        List<JokeModel> jokeModels = new ArrayList<>();
        Page<JokeModel> jokeModelPage = new PageImpl<>(jokeModels);

        Mockito.when(jokeRepository.findAllByPhraseIsContaining(query, PageRequest.of(page, qty)))
                .thenReturn(jokeModelPage);

        Page<? extends Phrase> jokeModelPage1 = fetchResourceService.getPhraseListWithQueryPagination(type, query
                , page,
                qty);

        Assertions.assertEquals(jokeModelPage, jokeModelPage1);
    }

    @Test
    public void getPhraseListWithQueryPaginationWrongPage() {

        int page = ReqParamPageValues.MIN_PAGE - 1;

        Assertions.assertThrows(InvalidParamValueException.class, () -> fetchResourceService.getPhraseListWithQueryPagination(ReqParamTypeValues.JOKE, "sample", page,
                ReqParamQtyValues.MINQTY));
    }

    @Test
    public void getPhraseListWithQueryPaginationWrongQuantity() {

        Assertions.assertThrows(InvalidParamValueException.class,
                () -> fetchResourceService.getPhraseListWithQueryPagination(ReqParamTypeValues.JOKE, "sample",
                ReqParamPageValues.MIN_PAGE, ReqParamQtyValues.MINQTY - 1));
    }


}

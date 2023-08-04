package com.roiceee.phraseapi;

import com.roiceee.phraseapi.resourceapi.dto.PhraseDTO;
import com.roiceee.phraseapi.resourceapi.exception.InvalidParamValueException;
import com.roiceee.phraseapi.resourceapi.model.JokeModel;
import com.roiceee.phraseapi.resourceapi.model.QuoteModel;
import com.roiceee.phraseapi.resourceapi.repository.JokeRepository;
import com.roiceee.phraseapi.resourceapi.repository.QuoteRepository;
import com.roiceee.phraseapi.resourceapi.service.FetchResourceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchResourceServiceTest {

    @InjectMocks
    private FetchResourceService fetchResourceService;

    @Mock
    private JokeRepository jokeRepository;

    @Mock
    private QuoteRepository quoteRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    public void testGetRandomPhraseHappyFlow() {
        String sampleJoke = "sample joke";
        String sampleQuote = "sample quote";
        String author = "author";

        String jokeType = "joke";
        String quoteType = "quote";

        when(jokeRepository.getRandomJoke()).thenReturn(new JokeModel(author, sampleJoke));
        when(quoteRepository.getRandomQuote()).thenReturn(new QuoteModel(author, sampleQuote));


        PhraseDTO jokePhraseDTO = fetchResourceService.getRandomPhrase(jokeType);
        PhraseDTO quotePhraseDTO = fetchResourceService.getRandomPhrase(quoteType);

        assertEquals(jokePhraseDTO.getAuthor(), author);
        assertEquals(jokePhraseDTO.getPhrase(), sampleJoke);
        assertEquals(jokePhraseDTO.getType(), jokeType);

        assertEquals(quotePhraseDTO.getAuthor(), author);
        assertEquals(quotePhraseDTO.getPhrase(), sampleQuote);
        assertEquals(quotePhraseDTO.getType(), quoteType);
    }

    @Test
    public void testGetRandomPhraseError() {
        String wrongType = "wrongType";

        Assertions.assertThrows(InvalidParamValueException.class, () -> {
            fetchResourceService.getRandomPhrase(wrongType);
        });

    }

    @Test
    public void testGetRandomPhraseListHappyFlow() {
        List<JokeModel> jokeModelList = List.of(new JokeModel("joke1", "author1"), new JokeModel("joke2",
                "author2"));
        List<QuoteModel> quoteModelList = List.of(new QuoteModel("quote1", "author1"), new QuoteModel("quote2",
                "author2"));
        int size = 2;
        when(jokeRepository.getRandomJokeList(size)).thenReturn(jokeModelList);
        when(quoteRepository.getRandomQuoteList(size)).thenReturn(quoteModelList);


        List<PhraseDTO> jokePhraseDTOList = fetchResourceService.getRandomPhraseList("joke", size);
        List<PhraseDTO> quotePhraseDTOList = fetchResourceService.getRandomPhraseList("quote", size);

        assertEquals(jokePhraseDTOList.size(), size);
        assertEquals(quotePhraseDTOList.size(), size);

    }

    @Test
    public void testGetRandomPhraseListError() {
        String wrongType = "wrongType";
        int size = 2;

        Assertions.assertThrows(InvalidParamValueException.class, () -> {
            fetchResourceService.getRandomPhraseList(wrongType, size);
        });


        int wrongSize = 0;

        Assertions.assertThrows(InvalidParamValueException.class, () -> {
            fetchResourceService.getRandomPhraseList("joke", wrongSize);
        });
    }

    @Test
    public void testGetRandomPhraseListWithQueryHappyFlow() {
        List<JokeModel> jokeModelList = List.of(new JokeModel("joke1", "author1"), new JokeModel("joke2",
                "author2"));
        List<QuoteModel> quoteModelList = List.of(new QuoteModel("quote1", "author1"), new QuoteModel("quote2",
                "author2"));
        int size = 2;
        String query = "query";

        when(jokeRepository.getRandomJokeListWithQuery(size, query)).thenReturn(jokeModelList);
        when(quoteRepository.getRandomQuoteListWithQuery(size, query)).thenReturn(quoteModelList);

        List<PhraseDTO> jokePhraseDTOList = fetchResourceService.getRandomPhraseListWithQuery("joke", size, query);
        List<PhraseDTO> quotePhraseDTOList = fetchResourceService.getRandomPhraseListWithQuery("quote", size, query);

        assertEquals(jokePhraseDTOList.size(), size);
        assertEquals(quotePhraseDTOList.size(), size);

}

    @Test
    public void testGetRandomPhraseListWithQueryError() {
        String wrongType = "wrongType";
        int size = 2;
        String query = "query";

        Assertions.assertThrows(InvalidParamValueException.class, () -> {
            fetchResourceService.getRandomPhraseListWithQuery(wrongType, size, query);
        });


        int wrongSize = 0;

        Assertions.assertThrows(InvalidParamValueException.class, () -> {
            fetchResourceService.getRandomPhraseListWithQuery("joke", wrongSize, query);
        });
    }


}

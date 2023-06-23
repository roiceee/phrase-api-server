package com.roiceee.phraseapi.resourceapi.service;

import com.roiceee.phraseapi.resourceapi.exception.InvalidParamValueException;
import com.roiceee.phraseapi.resourceapi.model.Phrase;
import com.roiceee.phraseapi.resourceapi.repository.JokeRepository;
import com.roiceee.phraseapi.resourceapi.repository.QuoteRepository;
import com.roiceee.phraseapi.resourceapi.util.Params;
import com.roiceee.phraseapi.resourceapi.util.ReqParamPageValues;
import com.roiceee.phraseapi.resourceapi.util.ReqParamQtyValues;
import com.roiceee.phraseapi.resourceapi.util.ReqParamTypeValues;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FetchResourceService {
    private final JokeRepository jokeRepository;
    private final QuoteRepository quoteRepository;


    public Phrase getRandomPhrase(String type) {
        return switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJoke();
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuote();
            default -> throw new InvalidParamValueException(type, Params.TYPE);
        };
    }

    public List<? extends Phrase> getRandomPhraseList(String type, int quantity) {
        validateQuantity(quantity);

        return switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJokeList(quantity);
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuoteList(quantity);
            default -> throw new InvalidParamValueException(type, Params.TYPE);
        };
    }

    public List<? extends Phrase> getRandomPhraseListWithQuery(String type, int quantity, String query) {
        validateQuantity(quantity);

        return switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJokeListWithQuery(quantity, query);
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuoteListWithQuery(quantity, query);
            default -> throw new InvalidParamValueException(type, Params.TYPE);
        };
    }



    public Page<? extends Phrase> getPhraseListWithQueryPagination(String type, String query, int page, int quantity) {
        validateQuantity(quantity);
        validatePageNumber(page);
        Pageable pageable = PageRequest.of(page, quantity);

        return switch (type) {
            case ReqParamTypeValues.JOKE ->
                    jokeRepository.findAllByPhraseIsContaining(query, pageable);
            case ReqParamTypeValues.QUOTE ->
                    quoteRepository.findAllByPhraseIsContaining(query, pageable);
            default -> throw new InvalidParamValueException(type, Params.TYPE);
        };
    }


    private void validateQuantity(int quantity) {
        if (quantity < ReqParamQtyValues.MINQTY ||
                quantity > ReqParamQtyValues.MAXQTY) {
            throw new InvalidParamValueException(quantity, Params.QTY);
        }
    }

    private void validatePageNumber(int page) {
        if (page < ReqParamPageValues.MIN_PAGE) {
            throw new InvalidParamValueException(page, Params.PAGE);
        }
    }
}

package com.roiceee.phraseapi.services;
import com.roiceee.phraseapi.exceptions.InvalidParamPageValueException;
import com.roiceee.phraseapi.exceptions.InvalidParamQuantityValueException;
import com.roiceee.phraseapi.exceptions.InvalidParamTypeValueException;
import com.roiceee.phraseapi.models.Phrase;
import com.roiceee.phraseapi.repositories.JokeRepository;
import com.roiceee.phraseapi.repositories.QuoteRepository;
import com.roiceee.phraseapi.util.ReqParamPageValues;
import com.roiceee.phraseapi.util.ReqParamQtyValues;
import com.roiceee.phraseapi.util.ReqParamTypeValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        validateQuantity(quantity);

        return switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJokeList(quantity);
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuoteList(quantity);
            default -> throw new InvalidParamTypeValueException(type);
        };
    }

    public List<? extends Phrase> getRandomPhraseListWithQuery(String type, int quantity, String query) {
        validateQuantity(quantity);

        return switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJokeListWithQuery(quantity, query);
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuoteListWithQuery(quantity, query);
            default -> throw new InvalidParamTypeValueException(type);
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
            default -> throw new InvalidParamTypeValueException(type);
        };
    }


    private void validateQuantity(int quantity) {
        if (quantity < ReqParamQtyValues.MINQTY ||
                quantity > ReqParamQtyValues.MAXQTY) {
            throw new InvalidParamQuantityValueException(quantity);
        }
    }

    private void validatePageNumber(int page) {
        if (page < ReqParamPageValues.MIN_PAGE) {
            throw new InvalidParamPageValueException(page);
        }
    }
}

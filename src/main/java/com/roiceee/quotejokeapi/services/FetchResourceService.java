package com.roiceee.quotejokeapi.services;
import com.roiceee.quotejokeapi.exceptions.InvalidParamPageValueException;
import com.roiceee.quotejokeapi.exceptions.InvalidParamQuantityValueException;
import com.roiceee.quotejokeapi.exceptions.InvalidParamTypeValueException;
import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.repositories.JokeRepository;
import com.roiceee.quotejokeapi.repositories.QuoteRepository;
import com.roiceee.quotejokeapi.util.ReqParamPageValues;
import com.roiceee.quotejokeapi.util.ReqParamQtyValues;
import com.roiceee.quotejokeapi.util.ReqParamTypeValues;
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

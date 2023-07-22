package com.roiceee.phraseapi.resourceapi.service;

import com.roiceee.phraseapi.resourceapi.dto.PhraseDTO;
import com.roiceee.phraseapi.resourceapi.exception.InvalidParamValueException;
import com.roiceee.phraseapi.resourceapi.model.JokeModel;
import com.roiceee.phraseapi.resourceapi.model.Phrase;
import com.roiceee.phraseapi.resourceapi.repository.JokeRepository;
import com.roiceee.phraseapi.resourceapi.repository.QuoteRepository;
import com.roiceee.phraseapi.resourceapi.util.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;


    public PhraseDTO getRandomPhrase(String type) {
        return convertToDto(switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJoke();
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuote();
            default -> throw new InvalidParamValueException(type, Params.TYPE);
        });
    }

    public List<PhraseDTO> getRandomPhraseList(String type, int quantity) {
        validateQuantity(quantity);

        return convertToDtoList(switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJokeList(quantity);
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuoteList(quantity);
            default -> throw new InvalidParamValueException(type, Params.TYPE);
        });

    }

    public List<PhraseDTO> getRandomPhraseListWithQuery(String type, int quantity, String query) {
        validateQuantity(quantity);

        return convertToDtoList(switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.getRandomJokeListWithQuery(quantity, query);
            case ReqParamTypeValues.QUOTE -> quoteRepository.getRandomQuoteListWithQuery(quantity, query);
            default -> throw new InvalidParamValueException(type, Params.TYPE);
        });
    }


    public Page<PhraseDTO> getPhraseListWithQueryPagination(String type, String query, int page,
                                                            int quantity) {
        validateQuantity(quantity);
        validatePageNumber(page);
        Pageable pageable = PageRequest.of(page, quantity);

        return convertToDtoPage(switch (type) {
            case ReqParamTypeValues.JOKE -> jokeRepository.findAllByPhraseIsContaining(query, pageable);
            case ReqParamTypeValues.QUOTE -> quoteRepository.findAllByPhraseIsContaining(query, pageable);
            default -> throw new InvalidParamValueException(type, Params.TYPE);
        });
    }

    // this doesn't need to have error messages to end users because it is meant to be consumed by the phrase api
    // client
    // app and not meant to be used by other apps
    public Page<PhraseDTO> getPhraseListToClientApp(String type, int page, int quantity, String query
            , String searchBy, String orderBy) {

        Pageable pageable = PageRequest.of(page, quantity);

        return switch (type) {
            case ReqParamTypeValues.JOKE -> searchJokeBy(searchBy, query, orderBy, pageable);
            case ReqParamTypeValues.QUOTE -> searchQuoteBy(searchBy, query, orderBy, pageable);
            default -> throw new InvalidParamValueException(searchBy, Params.SEARCH_BY);
        };

    }

    //break down the method above to smaller methods

    private Page<PhraseDTO> searchJokeBy(String searchBy, String query, String orderBy, Pageable pageable) {
        return convertToDtoPage(
                switch (searchBy) {
                    case ReqParamSearchByValues.PHRASE -> switch (orderBy) {
                        case ReqParamOrderByValues.ASC ->
                                jokeRepository.findAllByPhraseIsContainingIgnoreCaseOrderByTimestampAsc(query,
                                        pageable);
                        case ReqParamOrderByValues.DESC ->
                                jokeRepository.findAllByPhraseIsContainingIgnoreCaseOrderByTimestampDesc(query, pageable);
                        default -> throw new InvalidParamValueException(orderBy, Params.ORDER_BY);
                    };

                    case ReqParamSearchByValues.AUTHOR -> switch (orderBy) {
                        case ReqParamOrderByValues.ASC ->
                                jokeRepository.findAllByAuthorIsContainingIgnoreCaseOrderByTimestampAsc(query, pageable);
                        case ReqParamOrderByValues.DESC ->
                                jokeRepository.findAllByAuthorIsContainingIgnoreCaseOrderByTimestampDesc(query, pageable);
                        default -> throw new InvalidParamValueException(orderBy, Params.ORDER_BY);
                    };
                    default -> throw new InvalidParamValueException(searchBy, Params.SEARCH_BY);
                });
    }

    private Page<PhraseDTO> searchQuoteBy(String searchBy, String query, String orderBy, Pageable pageable) {
        return convertToDtoPage(
                switch (searchBy) {
                    case ReqParamSearchByValues.PHRASE -> switch (orderBy) {
                        case ReqParamOrderByValues.ASC ->
                                quoteRepository.findAllByPhraseIsContainingIgnoreCaseOrderByTimestampAsc(query, pageable);
                        case ReqParamOrderByValues.DESC ->
                                quoteRepository.findAllByPhraseIsContainingIgnoreCaseOrderByTimestampDesc(query, pageable);
                        default -> throw new InvalidParamValueException(orderBy, Params.ORDER_BY);
                    };

                    case ReqParamSearchByValues.AUTHOR -> switch (orderBy) {
                        case ReqParamOrderByValues.ASC ->
                                quoteRepository.findAllByAuthorIsContainingIgnoreCaseOrderByTimestampAsc(query, pageable);
                        case ReqParamOrderByValues.DESC ->
                                quoteRepository.findAllByAuthorIsContainingIgnoreCaseOrderByTimestampDesc(query, pageable);
                        default -> throw new InvalidParamValueException(orderBy, Params.ORDER_BY);
                    };
                    default -> throw new InvalidParamValueException(searchBy, Params.SEARCH_BY);
                });
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

    private PhraseDTO convertToDto(Phrase phrase) {
        PhraseDTO phraseDTO = modelMapper.map(phrase, PhraseDTO.class);
        if (phrase instanceof JokeModel) {
            phraseDTO.setType(ReqParamTypeValues.JOKE);
        } else {
            phraseDTO.setType(ReqParamTypeValues.QUOTE);
        }
        return phraseDTO;
    }

    private List<PhraseDTO> convertToDtoList(List<? extends Phrase> phrases) {
        return phrases.stream().map(this::convertToDto).toList();
    }

    private Page<PhraseDTO> convertToDtoPage(Page<? extends Phrase> phrases) {
        return phrases.map(this::convertToDto);
    }
}

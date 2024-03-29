package com.roiceee.phraseapi.phrasemanagement.service;

import com.roiceee.phraseapi.apikeymanagement.repository.ApiKeyRepository;
import com.roiceee.phraseapi.phrasemanagement.dto.AnalyticsDTO;
import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectAdminDTO;
import com.roiceee.phraseapi.phrasemanagement.exception.PhraseNotFoundException;
import com.roiceee.phraseapi.phrasemanagement.model.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.model.Status;
import com.roiceee.phraseapi.phrasemanagement.repository.PhraseManagementRepository;
import com.roiceee.phraseapi.phrasemanagement.util.PhraseManagementUtil;
import com.roiceee.phraseapi.phrasemanagement.util.SortOrders;
import com.roiceee.phraseapi.resourceapi.model.JokeModel;
import com.roiceee.phraseapi.resourceapi.model.QuoteModel;
import com.roiceee.phraseapi.resourceapi.repository.JokeRepository;
import com.roiceee.phraseapi.resourceapi.repository.QuoteRepository;
import com.roiceee.phraseapi.resourceapi.repository.RequestNumberRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@AllArgsConstructor
@Transactional
public class PhraseManagementAdminService {

    private final PhraseManagementRepository phraseManagementRepository;
    private final RequestNumberRepository requestNumberRepository;
    private final ApiKeyRepository apiKeyRepository;
    private final JokeRepository jokeRepository;
    private final QuoteRepository quoteRepository;
    private final ModelMapper modelMapper;

    public PhrasePostObjectAdminDTO approvePhrase(Long id) {
        PhrasePostObject phrasePostObject =
                phraseManagementRepository.findById(id).orElseThrow(PhraseNotFoundException::new);

        phrasePostObject.setStatus(Status.APPROVED);
        phrasePostObject.setDateModifiedByAdmin(PhraseManagementUtil.getCurrentTimestamp());

        addToResource(phrasePostObject);

        phraseManagementRepository.save(phrasePostObject);

        return convertPhrasePostObjectToDTO(phrasePostObject);
    }

    public PhrasePostObjectAdminDTO rejectPhrase(Long id) {
        PhrasePostObject phrasePostObject =
                phraseManagementRepository.findById(id).orElseThrow(PhraseNotFoundException::new);

        phrasePostObject.setStatus(Status.REJECTED);
        phrasePostObject.setDateModifiedByAdmin(PhraseManagementUtil.getCurrentTimestamp());

        deleteFromResourceRepository(phrasePostObject);

        phraseManagementRepository.save(phrasePostObject);

        return convertPhrasePostObjectToDTO(phrasePostObject);
    }

    public PhrasePostObjectAdminDTO pendPhrase(Long id) {
        PhrasePostObject phrasePostObject =
                phraseManagementRepository.findById(id).orElseThrow(PhraseNotFoundException::new);

        phrasePostObject.setStatus(Status.PENDING);
        phrasePostObject.setDateModifiedByAdmin(PhraseManagementUtil.getCurrentTimestamp());

        deleteFromResourceRepository(phrasePostObject);

        phraseManagementRepository.save(phrasePostObject);

        return convertPhrasePostObjectToDTO(phrasePostObject);
    }

    public void deletePhrase(Long id) {
        PhrasePostObject phrasePostObject =
                phraseManagementRepository.findById(id).orElseThrow(PhraseNotFoundException::new);
        deleteFromResourceRepository(phrasePostObject);
        phraseManagementRepository.delete(phrasePostObject);
    }

    public Page<PhrasePostObjectAdminDTO> getAllPhrases(int page, String sortBy) {
        Page<PhrasePostObjectAdminDTO> res = new PageImpl<>(new ArrayList<>());
        if (sortBy.equals(SortOrders.DATE_SUBMITTED))
            res = phraseManagementRepository.findAllByOrderByDateSubmitted(pageableOf(page)).map(this::convertPhrasePostObjectToDTO);
        else if (sortBy.equals(SortOrders.ALPHABETICAL))
            res = phraseManagementRepository.findAllByOrderByPhrase(pageableOf(page)).map(this::convertPhrasePostObjectToDTO);
        return res;
    }

    public Page<PhrasePostObjectAdminDTO> getPhrasesByStatus(int page, String sortBy, Status status) {
        Page<PhrasePostObjectAdminDTO> res = new PageImpl<>(new ArrayList<>());
        if (sortBy.equals(SortOrders.DATE_SUBMITTED))
            res = phraseManagementRepository.findAllByStatusOrderByDateSubmitted(pageableOf(page),
                    status).map(this::convertPhrasePostObjectToDTO);
        else if (sortBy.equals(SortOrders.ALPHABETICAL))
            res = phraseManagementRepository.findAllByStatusOrderByPhrase(pageableOf(page), status).map(this::convertPhrasePostObjectToDTO);

        return res;
    }


    public AnalyticsDTO getAnalytics() {

        long quotes = quoteRepository.count();
        long jokes = jokeRepository.count();
        return new AnalyticsDTO((quotes + jokes), quotes, jokes,
                phraseManagementRepository.count(), phraseManagementRepository.countByStatus(Status.APPROVED),
                phraseManagementRepository.countByStatus(Status.REJECTED),
                phraseManagementRepository.countByStatus(Status.PENDING), requestNumberRepository.getCountSum(),
                apiKeyRepository.count());
    }

    public Pageable pageableOf(int page) {
        return PageRequest.of(page, 12);
    }

    public void addToResource(PhrasePostObject phrasePostObject) {
        switch (phrasePostObject.getType()) {
            case "joke" -> {
                JokeModel joke = new JokeModel();
                joke.setAuthor(phrasePostObject.getAuthor());
                joke.setPhrase(phrasePostObject.getPhrase());
                joke.setPhraseManagementID(phrasePostObject.getId());
                jokeRepository.save(joke);
            }
            case "quote" -> {
                QuoteModel quoteModel = new QuoteModel();
                quoteModel.setAuthor(phrasePostObject.getAuthor());
                quoteModel.setPhrase(phrasePostObject.getPhrase());
                quoteModel.setPhraseManagementID(phrasePostObject.getId());
                quoteRepository.save(quoteModel);
            }
        }
    }

    public void deleteFromResourceRepository(PhrasePostObject phrasePostObject) {
        switch (phrasePostObject.getType()) {
            case "joke" -> jokeRepository.deleteByPhraseManagementID(phrasePostObject.getId());

            case "quote" -> quoteRepository.deleteByPhraseManagementID(phrasePostObject.getId());

        }
    }

    private PhrasePostObjectAdminDTO convertPhrasePostObjectToDTO(PhrasePostObject phrasePostObject) {
        return modelMapper.map(phrasePostObject, PhrasePostObjectAdminDTO.class);
    }
}

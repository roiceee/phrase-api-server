package com.roiceee.phraseapi.phrasemanagement.service;

import com.roiceee.phraseapi.phrasemanagement.exception.PhraseNotFoundException;
import com.roiceee.phraseapi.phrasemanagement.model.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.model.Status;
import com.roiceee.phraseapi.phrasemanagement.repository.PhraseManagementRepository;
import com.roiceee.phraseapi.resourceapi.model.JokeModel;
import com.roiceee.phraseapi.resourceapi.model.QuoteModel;
import com.roiceee.phraseapi.resourceapi.repository.JokeRepository;
import com.roiceee.phraseapi.resourceapi.repository.QuoteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
public class PhraseManagementAdminService {

    private final PhraseManagementRepository phraseManagementRepository;
    private final JokeRepository jokeRepository;
    private final QuoteRepository quoteRepository;

    public PhrasePostObject approvePhrase(Long id) {
        Optional<PhrasePostObject> res = phraseManagementRepository.findById(id);
        if (res.isEmpty()) {
            throw new PhraseNotFoundException();
        }
        PhrasePostObject phrasePostObject = res.get();
        phrasePostObject.setStatus(Status.ACCEPTED);
        addToResource(phrasePostObject);
        phraseManagementRepository.save(phrasePostObject);
        return phrasePostObject;
    }

    public PhrasePostObject rejectPhrase(Long id) {
        Optional<PhrasePostObject> res = phraseManagementRepository.findById(id);
        if (res.isEmpty()) {
            throw new PhraseNotFoundException();
        }
        PhrasePostObject phrasePostObject = res.get();
        phrasePostObject.setStatus(Status.REJECTED);
        deleteFromResourceById(phrasePostObject);
        phraseManagementRepository.save(phrasePostObject);
        return phrasePostObject;
    }

    public Page<PhrasePostObject> getAllPhrases(int page) {
        return phraseManagementRepository.findAll(pageableOf(page));
    }

    public Page<PhrasePostObject> getAllPendingPhrases(int page) {
        return phraseManagementRepository.findAllByStatus(pageableOf(page), Status.PENDING);
    }

    public Page<PhrasePostObject> getAllApprovedPhrases(int page) {
        return phraseManagementRepository.findAllByStatus(pageableOf(page), Status.ACCEPTED);
    }

    public Page<PhrasePostObject> getAllRejectedPhrases(int page) {
        return phraseManagementRepository.findAllByStatus(pageableOf(page), Status.REJECTED);
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
                joke.setPhrasemanagementID(phrasePostObject.getId());
                jokeRepository.save(joke);
            }
            case "quote" -> {
                QuoteModel quoteModel = new QuoteModel();
                quoteModel.setAuthor(phrasePostObject.getAuthor());
                quoteModel.setPhrase(phrasePostObject.getPhrase());
                quoteModel.setPhrasemanagementID(phrasePostObject.getId());
                quoteRepository.save(quoteModel);
            }
        }
    }

    public void deleteFromResourceById(PhrasePostObject phrasePostObject) {
        switch (phrasePostObject.getType()) {
            case "joke" -> {
                jokeRepository.deleteByPhrasemanagementID(phrasePostObject.getId());
            }
            case "quote" -> {
                quoteRepository.deleteByPhrasemanagementID(phrasePostObject.getId());
            }
        }
    }
}

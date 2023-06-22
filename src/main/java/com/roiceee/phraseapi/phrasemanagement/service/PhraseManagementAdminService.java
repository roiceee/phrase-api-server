package com.roiceee.phraseapi.phrasemanagement.service;

import com.roiceee.phraseapi.phrasemanagement.exception.PhraseNotFoundException;
import com.roiceee.phraseapi.phrasemanagement.model.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.model.Status;
import com.roiceee.phraseapi.phrasemanagement.repository.PhraseManagementRepository;
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

    public PhrasePostObject approvePhrase(Long id) {
        Optional<PhrasePostObject> res = phraseManagementRepository.findById(id);
        if (res.isEmpty()) {
            throw new PhraseNotFoundException();
        }
        PhrasePostObject phrasePostObject = res.get();
        phrasePostObject.setStatus(Status.ACCEPTED);
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
}

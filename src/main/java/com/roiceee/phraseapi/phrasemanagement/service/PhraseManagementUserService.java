package com.roiceee.phraseapi.phrasemanagement.service;

import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectUserDTO;
import com.roiceee.phraseapi.phrasemanagement.exception.*;
import com.roiceee.phraseapi.phrasemanagement.model.PhraseCount;
import com.roiceee.phraseapi.phrasemanagement.model.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.model.Status;
import com.roiceee.phraseapi.phrasemanagement.repository.PhraseManagementRepository;
import com.roiceee.phraseapi.phrasemanagement.util.PhraseManagementUtil;
import com.roiceee.phraseapi.phrasemanagement.util.SortOrders;
import com.roiceee.phraseapi.resourceapi.repository.JokeRepository;
import com.roiceee.phraseapi.resourceapi.repository.QuoteRepository;
import com.roiceee.phraseapi.resourceapi.util.ReqParamTypeValues;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PhraseManagementUserService {

    private final PhraseManagementRepository phraseManagementRepository;
    private final JokeRepository jokeRepository;
    private final QuoteRepository quoteRepository;
    private final ModelMapper modelMapper;


    public PhrasePostObjectUserDTO addPhrase(String userID, PhrasePostObjectUserDTO phrasePostObjectUserDTO) {

        checkIfPhraseIsEmpty(phrasePostObjectUserDTO.getPhrase());
        checkIfPhraseExists(phrasePostObjectUserDTO.getPhrase());
        checkIfTypeExists(phrasePostObjectUserDTO.getType());
        checkIfMaxPhrasesExceeds(userID);

        //set id to null to avoid id conflict, let the database handle it
        PhrasePostObject phrasePostObject = new PhrasePostObject(userID, phrasePostObjectUserDTO.getType(),
                phrasePostObjectUserDTO.getAuthor(), phrasePostObjectUserDTO.getPhrase());

        PhrasePostObject res = phraseManagementRepository.save(phrasePostObject);

        return convertPhrasePostObjectToDTO(res);
    }

    public void deletePhrase(String userID, long id) {
        checkIfPhraseExistsByIdAndUserId(id, userID);
        deletePhraseFromResourceRepository(id);
        phraseManagementRepository.deletePhrasePostObjectByIdAndUserId(id, userID);
    }

    public PhrasePostObjectUserDTO editPhrase(String userID, PhrasePostObjectUserDTO phrasePostObjectUserDTO) {

        checkIfPhraseIsEmpty(phrasePostObjectUserDTO.getPhrase());
        checkIfTypeExists(phrasePostObjectUserDTO.getType());
        checkIfUpdatedPhraseIsSameAsOldPhrase(phrasePostObjectUserDTO.getPhrase(), phrasePostObjectUserDTO.getId());

        PhrasePostObject phrasePostObject =
                phraseManagementRepository.findByIdAndUserId(phrasePostObjectUserDTO.getId(), userID).orElseThrow(PhraseNotFoundException::new);

        phrasePostObject.setType(phrasePostObjectUserDTO.getType());
        phrasePostObject.setAuthor(phrasePostObjectUserDTO.getAuthor());
        phrasePostObject.setPhrase(phrasePostObjectUserDTO.getPhrase());
        phrasePostObject.setStatus(Status.PENDING);
        phrasePostObject.setDateSubmitted(PhraseManagementUtil.getCurrentTimestamp());

        deletePhraseFromResourceRepository(phrasePostObject.getId());
        PhrasePostObject res = phraseManagementRepository.save(phrasePostObject);
        return convertPhrasePostObjectToDTO(res);
    }

    public List<PhrasePostObjectUserDTO> getAllPhrases(String userID, String sortBy) {
        List<PhrasePostObject> res = new ArrayList<>();

        if (sortBy.equals(SortOrders.DATE_SUBMITTED)) {
            res = phraseManagementRepository.findAllByUserIdOrderByDateSubmittedAsc(userID);
        } else if (sortBy.equals(SortOrders.ALPHABETICAL)) {
            res = phraseManagementRepository.findAllByUserIdOrderByPhraseAsc(userID);
        }

        return res.stream().map(obj -> modelMapper.map(obj,
                PhrasePostObjectUserDTO.class)).toList();
    }

    public PhraseCount getPhraseCount(String userId) {
        return new PhraseCount(PhraseManagementUtil.MAX_PHRASES,
                phraseManagementRepository.countPhrasePostObjectByUserId(userId));
    }

    private void checkIfMaxPhrasesExceeds(String userId) {
        if (phraseManagementRepository.countPhrasePostObjectByUserId(userId) < PhraseManagementUtil.MAX_PHRASES) {
            return;
        }
        throw new MaxPhrasesLimitReachedException();
    }


    private void checkIfTypeExists(String type) {
        if (ReqParamTypeValues.validTypes.contains(type)) {
            return;
        }
        throw new InvalidPhraseTypeException();
    }

    private void checkIfPhraseExistsByIdAndUserId(long id, String userID) {
        if (phraseManagementRepository.existsPhrasePostObjectByIdAndUserId(id, userID)) {
            return;
        }
        throw new PhraseNotFoundException();
    }

    private void checkIfPhraseIsEmpty(String phrase) {
        if (!phrase.isEmpty() || !phrase.isBlank()) {
            return;
        }
        throw new PhraseIsEmptyException();
    }

    private void checkIfPhraseExists(String phrase) {
        if (!phraseManagementRepository.existsPhrasePostObjectByPhrase(phrase)) {
            return;
        }
        throw new PhraseAlreadyExistsException();
    }

    private void checkIfUpdatedPhraseIsSameAsOldPhrase(String phrase, long phraseID) {
        if (phraseManagementRepository.existsPhrasePostObjectByPhraseAndId(phrase, phraseID)) {
            return;
        }
        checkIfPhraseExists(phrase);
    }

    private void deletePhraseFromResourceRepository(long phraseID) {
        jokeRepository.deleteByPhraseManagementID(phraseID);
        quoteRepository.deleteByPhraseManagementID(phraseID);
    }

    private PhrasePostObjectUserDTO convertPhrasePostObjectToDTO(PhrasePostObject phrasePostObject) {
        return modelMapper.map(phrasePostObject, PhrasePostObjectUserDTO.class);
    }


}

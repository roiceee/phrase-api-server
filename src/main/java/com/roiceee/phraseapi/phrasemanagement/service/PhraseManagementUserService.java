package com.roiceee.phraseapi.phrasemanagement.service;

import com.roiceee.phraseapi.phrasemanagement.exception.*;
import com.roiceee.phraseapi.phrasemanagement.model.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.model.Status;
import com.roiceee.phraseapi.phrasemanagement.repository.PhraseManagementRepository;
import com.roiceee.phraseapi.phrasemanagement.util.PhraseManagementUtil;
import com.roiceee.phraseapi.resourceapi.repository.JokeRepository;
import com.roiceee.phraseapi.resourceapi.repository.QuoteRepository;
import com.roiceee.phraseapi.resourceapi.util.ReqParamTypeValues;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PhraseManagementUserService {

    private final PhraseManagementRepository phraseManagementRepository;
    private final JokeRepository jokeRepository;
    private final QuoteRepository quoteRepository;


    public PhrasePostObject addPhrase(String userID, PhrasePostObject phrasePostObject) {

        checkIfPhraseIsEmpty(phrasePostObject.getPhrase());
        checkIfPhraseExists(phrasePostObject.getPhrase());
        checkIfTypeExists(phrasePostObject.getType());
        checkIfMaxPhrasesExceeds(userID);

        //make id null to avoid id conflict, let the database generate the id
        phrasePostObject.setId(null);
        phrasePostObject.setStatus(Status.PENDING);
        phrasePostObject.setUserId(userID);

        phraseManagementRepository.save(phrasePostObject);

        return phrasePostObject;
    }

    public void deletePhrase(String userID, long id) {
        checkIfPhraseExistsByIdAndUserId(id, userID);
        deletePhraseFromResourcesByID(id);
        phraseManagementRepository.deletePhrasePostObjectByIdAndUserId(id, userID);
    }

    public PhrasePostObject editPhrase(String userID, PhrasePostObject phrasePostObject) {

        checkIfPhraseIsEmpty(phrasePostObject.getPhrase());
        checkIfTypeExists(phrasePostObject.getType());
        checkIfPhraseExistsByIdAndUserId(phrasePostObject.getId(), userID);
        checkIfUpdatedPhraseIsSameAsOldPhrase(phrasePostObject.getPhrase(), phrasePostObject.getId());

        phrasePostObject.setStatus(Status.PENDING);
        phrasePostObject.setUserId(userID);

        deletePhraseFromResourcesByID(phrasePostObject.getId());
        phraseManagementRepository.save(phrasePostObject);
        return phrasePostObject;
    }

    public List<PhrasePostObject> getAllPhrases(String userID) {
       return phraseManagementRepository.findAllByUserId(userID);
    }

    public int getNumberOfPhrases(String userId) {
        return (int) phraseManagementRepository.countPhrasePostObjectByUserId(userId);
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

    private void checkIfPhraseIsEmpty(String phrase) {
        if (!phrase.isEmpty() || !phrase.isBlank()) {
            return;
        }
        throw new PhraseIsEmptyException();
    }

    private void checkIfPhraseExistsByIdAndUserId(long phraseID, String userID) {
        if (phraseManagementRepository.existsPhrasePostObjectByIdAndUserId(phraseID, userID)) {
            return;
        }
        throw new PhraseNotFoundException();
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

    private void deletePhraseFromResourcesByID(long phraseID) {
        jokeRepository.deleteByPhrasemanagementID(phraseID);
        quoteRepository.deleteByPhrasemanagementID(phraseID);
    }

}

package com.roiceee.phraseapi.phrasemanagement.services;

import com.roiceee.phraseapi.phrasemanagement.exceptions.*;
import com.roiceee.phraseapi.phrasemanagement.models.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.repositories.PhraseManagementRepository;
import com.roiceee.phraseapi.phrasemanagement.util.PhraseManagementUtil;
import com.roiceee.phraseapi.resourceapi.util.ReqParamTypeValues;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhraseManagementService {

    private final PhraseManagementRepository phraseManagementRepository;

    public PhraseManagementService(PhraseManagementRepository phraseManagementRepository) {
        this.phraseManagementRepository = phraseManagementRepository;
    }

    public PhrasePostObject addPhrase(String userID, PhrasePostObject phrasePostObject) {
        checkIfPhraseIsNotEmpty(phrasePostObject.getPhrase());
        checkIfPhraseDoesntExist(phrasePostObject.getPhrase());
        checkIfTypeExists(phrasePostObject.getType());
        checkIfMaxPhrasesExceeds(userID);
        phrasePostObject.setUserId(userID);
        phraseManagementRepository.save(phrasePostObject);
        return phrasePostObject;
    }

    public void deletePhrase(String userID, long phraseID) {
        checkIfPhraseExistsByIdAndUserId(phraseID, userID);
        phraseManagementRepository.deletePhrasePostObjectByIdAndUserId(phraseID, userID);
    }

    public PhrasePostObject editPhrase(String userID, PhrasePostObject phrasePostObject) {
        checkIfPhraseIsNotEmpty(phrasePostObject.getPhrase());
        checkIfTypeExists(phrasePostObject.getType());
        phraseManagementRepository.updatePhrasePostObject(phrasePostObject.getAuthor(),
                phrasePostObject.getPhrase(), userID, phrasePostObject.getId());
        return phrasePostObject;
    }

    public List<PhrasePostObject> getAllPhrases(String userID) {
       return phraseManagementRepository.findAllByUserId(userID);
    }

    public int getNumberOfPhrases(String userId) {
        return (int) phraseManagementRepository.countPhrasePostObjectByUserId(userId);
    }

    private void checkIfMaxPhrasesExceeds(String userId) {
        if (phraseManagementRepository.countPhrasePostObjectByUserId(userId) <= PhraseManagementUtil.MAX_PHRASES) {
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

    private void checkIfPhraseIsNotEmpty(String phrase) {
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

    private void checkIfPhraseDoesntExist(String phrase) {
        if (!phraseManagementRepository.existsPhrasePostObjectByPhrase(phrase)) {
            return;
        }
        throw new PhraseAlreadyExistsException();
    }
}

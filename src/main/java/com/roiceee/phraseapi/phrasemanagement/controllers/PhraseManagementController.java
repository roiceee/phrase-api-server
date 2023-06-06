package com.roiceee.phraseapi.phrasemanagement.controllers;

import com.roiceee.phraseapi.phrasemanagement.dto.DeletePhraseDTO;
import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectDTO;
import com.roiceee.phraseapi.phrasemanagement.models.PhraseManagementMetadata;
import com.roiceee.phraseapi.phrasemanagement.models.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.services.PhraseManagementService;
import com.roiceee.phraseapi.phrasemanagement.util.PhraseManagementUtil;
import com.roiceee.phraseapi.util.Origins;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin(origins = {Origins.LOCAL, Origins.PROD})
@RequestMapping("phrase-management")
public class PhraseManagementController {
    private final PhraseManagementService phraseManagementService;

    private final ModelMapper modelMapper;

//    private final Logger logger = LoggerFactory.getLogger(PhraseManagementController.class);

    public PhraseManagementController(PhraseManagementService phraseManagementService, ModelMapper modelMapper) {
        this.phraseManagementService = phraseManagementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("add")
    public ResponseEntity<PhrasePostObjectDTO> addPhrase(Authentication authentication,
                                                         @RequestBody PhrasePostObjectDTO phrasePostObjectDTO) {

        PhrasePostObject phrasePostObject = modelMapper.map(phrasePostObjectDTO, PhrasePostObject.class);

        PhrasePostObject res = phraseManagementService.addPhrase(authentication.getName(),
                phrasePostObject);

        PhrasePostObjectDTO returnedObject = modelMapper.map(res, PhrasePostObjectDTO.class);
        return ResponseEntity.ok().body(returnedObject);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deletePhrase(Authentication authentication,
                                               @RequestBody DeletePhraseDTO deletePhraseDTO) {
        phraseManagementService.deletePhrase(authentication.getName(), deletePhraseDTO.getId());
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PatchMapping("update")
    public ResponseEntity<PhrasePostObject> editPhrase(Authentication authentication,
                                                       PhrasePostObject phrasePostObject) {
        PhrasePostObject returnedObject = phraseManagementService.editPhrase(authentication.getName(),
                phrasePostObject);
        return ResponseEntity.ok().body(returnedObject);
    }

    @GetMapping("get")
    public ResponseEntity<List<PhrasePostObject>> getAllPhrases(Authentication authentication) {
        return ResponseEntity.ok().body(phraseManagementService.getAllPhrases(authentication.getName()));
    }

    @GetMapping("get-metadata")
    public ResponseEntity<PhraseManagementMetadata> getMetadata(Authentication authentication) {
        int numberOfPhrases = phraseManagementService.getNumberOfPhrases(authentication.getName());
        PhraseManagementMetadata phraseManagementMetadata =
                new PhraseManagementMetadata(PhraseManagementUtil.MAX_PHRASES, numberOfPhrases);
        return ResponseEntity.ok().body(phraseManagementMetadata);
    }

}

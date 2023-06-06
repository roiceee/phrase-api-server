package com.roiceee.phraseapi.phrasemanagement.controllers;

import com.roiceee.phraseapi.phrasemanagement.dto.DeletePhraseDTO;
import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectDTO;
import com.roiceee.phraseapi.phrasemanagement.models.PhraseManagementMetadata;
import com.roiceee.phraseapi.phrasemanagement.models.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.services.PhraseManagementService;
import com.roiceee.phraseapi.phrasemanagement.util.PhraseManagementUtil;
import com.roiceee.phraseapi.util.Origins;
import org.modelmapper.ModelMapper;
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
    public ResponseEntity<PhrasePostObjectDTO> editPhrase(Authentication authentication,
                                                         @RequestBody PhrasePostObjectDTO phrasePostObjectDTO) {

        PhrasePostObject phrasePostObject = modelMapper.map(phrasePostObjectDTO, PhrasePostObject.class);

        PhrasePostObject res = phraseManagementService.editPhrase(authentication.getName(),
                phrasePostObject);

        PhrasePostObjectDTO returnedObject = modelMapper.map(res, PhrasePostObjectDTO.class);

        return ResponseEntity.ok().body(returnedObject);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<PhrasePostObjectDTO>> getAllPhrases(Authentication authentication) {

        List<PhrasePostObject> res = phraseManagementService.getAllPhrases(authentication.getName());

        List<PhrasePostObjectDTO> returnList = res.stream().map(obj -> modelMapper.map(obj,
                PhrasePostObjectDTO.class)).toList();
        return ResponseEntity.ok().body(returnList);
    }

    @GetMapping("get-metadata")
    public ResponseEntity<PhraseManagementMetadata> getMetadata(Authentication authentication) {

        int numberOfPhrases = phraseManagementService.getNumberOfPhrases(authentication.getName());

        PhraseManagementMetadata phraseManagementMetadata =
                new PhraseManagementMetadata(PhraseManagementUtil.MAX_PHRASES, numberOfPhrases);

        return ResponseEntity.ok().body(phraseManagementMetadata);
    }

}

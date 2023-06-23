package com.roiceee.phraseapi.phrasemanagement.controller;

import com.roiceee.phraseapi.phrasemanagement.dto.DeletePhraseDTO;
import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectDTO;
import com.roiceee.phraseapi.phrasemanagement.model.PhraseManagementMetadata;
import com.roiceee.phraseapi.phrasemanagement.model.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.service.PhraseManagementUserService;
import com.roiceee.phraseapi.phrasemanagement.util.PhraseManagementUtil;
import com.roiceee.phraseapi.util.Origins;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = {Origins.LOCAL, Origins.PROD})
@RequestMapping("phrase-management/user")
@AllArgsConstructor
public class PhraseManagementUserController {
    private final PhraseManagementUserService phraseManagementService;
    private final ModelMapper modelMapper;

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

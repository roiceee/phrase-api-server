package com.roiceee.phraseapi.phrasemanagement.controller;

import com.roiceee.phraseapi.phrasemanagement.dto.DeletePhraseDTO;
import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectUserDTO;
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
    public ResponseEntity<PhrasePostObjectUserDTO> addPhrase(Authentication authentication,
                                                             @RequestBody PhrasePostObjectUserDTO phrasePostObjectUserDTO) {

        PhrasePostObjectUserDTO res = phraseManagementService.addPhrase(authentication.getName(),
                phrasePostObjectUserDTO);

        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deletePhrase(Authentication authentication,
                                               @RequestBody DeletePhraseDTO deletePhraseDTO) {

        phraseManagementService.deletePhrase(authentication.getName(), deletePhraseDTO.getId());

        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PatchMapping("update")
    public ResponseEntity<PhrasePostObjectUserDTO> editPhrase(Authentication authentication,
                                                              @RequestBody PhrasePostObjectUserDTO phrasePostObjectUserDTO) {

        PhrasePostObjectUserDTO res = phraseManagementService.editPhrase(authentication.getName(),
                phrasePostObjectUserDTO);

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<PhrasePostObjectUserDTO>> getAllPhrases(Authentication authentication) {

        List<PhrasePostObject> res = phraseManagementService.getAllPhrases(authentication.getName());

        List<PhrasePostObjectUserDTO> returnList = res.stream().map(obj -> modelMapper.map(obj,
                PhrasePostObjectUserDTO.class)).toList();

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

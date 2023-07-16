package com.roiceee.phraseapi.phrasemanagement.controller;

import com.roiceee.phraseapi.phrasemanagement.dto.DeletePhraseDTO;
import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectUserDTO;
import com.roiceee.phraseapi.phrasemanagement.model.PhraseCount;
import com.roiceee.phraseapi.phrasemanagement.service.PhraseManagementUserService;
import com.roiceee.phraseapi.phrasemanagement.util.SortOrders;
import com.roiceee.phraseapi.util.Origins;
import lombok.AllArgsConstructor;
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

    @GetMapping(value = "get-all")
    public ResponseEntity<List<PhrasePostObjectUserDTO>> getAllPhrases(Authentication authentication,
                                                                       @RequestParam(defaultValue =
                                                                               SortOrders.DATE_SUBMITTED) String sortBy) {

        return ResponseEntity.ok().body(phraseManagementService.getAllPhrases(authentication.getName(), sortBy));
    }

    @GetMapping("get-metadata")
    public ResponseEntity<PhraseCount> getMetadata(Authentication authentication) {

        return ResponseEntity.ok().body(phraseManagementService.getPhraseCount(authentication.getName()));
    }

}

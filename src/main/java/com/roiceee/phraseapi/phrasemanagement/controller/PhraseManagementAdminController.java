package com.roiceee.phraseapi.phrasemanagement.controller;

import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectDTO;
import com.roiceee.phraseapi.phrasemanagement.model.AnalyticsDTO;
import com.roiceee.phraseapi.phrasemanagement.model.PhrasePostObject;
import com.roiceee.phraseapi.phrasemanagement.service.PhraseManagementAdminService;
import com.roiceee.phraseapi.util.Origins;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {Origins.LOCAL, Origins.PROD})
@RequestMapping("phrase-management/admin")
public class PhraseManagementAdminController {

    private final PhraseManagementAdminService phraseManagementAdminService;
    private final ModelMapper modelMapper;

    @GetMapping("check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok().body("Is an admin.");
    }


    @GetMapping("get-all")
    public ResponseEntity<Page<PhrasePostObjectDTO>> getAllPhrases(
            @RequestParam(defaultValue = "0") Integer pageNo
    ) {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAllPhrases(pageNo)
                .map(phrasePostObject -> modelMapper.map(phrasePostObject, PhrasePostObjectDTO.class)));
    }

    @GetMapping("get-pending")
    public ResponseEntity<Page<PhrasePostObjectDTO>> getAllPendingPhrases(
            @RequestParam(defaultValue = "0") Integer pageNo
    ) {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAllPendingPhrases(pageNo)
                .map(phrasePostObject -> modelMapper.map(phrasePostObject, PhrasePostObjectDTO.class)));
    }

    @GetMapping("get-approved")
    public ResponseEntity<Page<PhrasePostObjectDTO>> getAllApprovedPhrases(
            @RequestParam(defaultValue = "0") Integer pageNo
    ) {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAllApprovedPhrases(pageNo)
                .map(phrasePostObject -> modelMapper.map(phrasePostObject, PhrasePostObjectDTO.class)));
    }

    @GetMapping("get-rejected")
    public ResponseEntity<Page<PhrasePostObjectDTO>> getAllRejectedPhrases(
            @RequestParam(defaultValue = "0") Integer pageNo
    ) {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAllRejectedPhrases(pageNo)
                .map(phrasePostObject -> modelMapper.map(phrasePostObject, PhrasePostObjectDTO.class)));
    }

    @PatchMapping("approve")
    public ResponseEntity<PhrasePostObjectDTO> approvePhrase(@RequestParam Long id) {

        PhrasePostObject res = phraseManagementAdminService.approvePhrase(id);
        PhrasePostObjectDTO returnedObject = modelMapper.map(res, PhrasePostObjectDTO.class);
        return ResponseEntity.ok().body(returnedObject);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deletePhrase(@RequestParam Long id) {

        phraseManagementAdminService.deletePhrase(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PatchMapping("reject")
    public ResponseEntity<PhrasePostObjectDTO> rejectPhrase(@RequestParam Long id) {

        PhrasePostObject res = phraseManagementAdminService.rejectPhrase(id);
        PhrasePostObjectDTO returnedObject = modelMapper.map(res, PhrasePostObjectDTO.class);
        return ResponseEntity.ok().body(returnedObject);
    }

    @GetMapping("get-analytics")
    public ResponseEntity<AnalyticsDTO> getAnalytics() {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAnalytics());
    }

}

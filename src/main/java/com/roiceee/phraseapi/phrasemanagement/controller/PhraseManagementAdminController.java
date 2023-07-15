package com.roiceee.phraseapi.phrasemanagement.controller;

import com.roiceee.phraseapi.phrasemanagement.dto.AnalyticsDTO;
import com.roiceee.phraseapi.phrasemanagement.dto.PhrasePostObjectAdminDTO;
import com.roiceee.phraseapi.phrasemanagement.service.PhraseManagementAdminService;
import com.roiceee.phraseapi.util.Origins;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {Origins.LOCAL, Origins.PROD})
@RequestMapping("phrase-management/admin")
public class PhraseManagementAdminController {

    private final PhraseManagementAdminService phraseManagementAdminService;

    @GetMapping("check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok().body("Is an admin.");
    }

    @GetMapping("get-all/{pageNo}")
    public ResponseEntity<Page<PhrasePostObjectAdminDTO>> getAllPhrases(
            @PathVariable("pageNo") Integer pageNo

    ) {

        return ResponseEntity.ok().body(phraseManagementAdminService.getAllPhrases(pageNo));
    }

    @GetMapping("get-pending/{pageNo}")
    public ResponseEntity<Page<PhrasePostObjectAdminDTO>> getAllPendingPhrases(
            @PathVariable("pageNo") Integer pageNo
    ) {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAllPendingPhrases(pageNo));
    }

    @GetMapping("get-approved/{pageNo}")
    public ResponseEntity<Page<PhrasePostObjectAdminDTO>> getAllApprovedPhrases(
            @PathVariable("pageNo") Integer pageNo
    ) {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAllApprovedPhrases(pageNo));
    }

    @GetMapping("get-rejected/{pageNo}")
    public ResponseEntity<Page<PhrasePostObjectAdminDTO>> getAllRejectedPhrases(
            @PathVariable("pageNo") Integer pageNo
    ) {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAllRejectedPhrases(pageNo));
    }

    @PatchMapping("approve")
    public ResponseEntity<PhrasePostObjectAdminDTO> approvePhrase(@RequestParam Long id) {

        PhrasePostObjectAdminDTO res = phraseManagementAdminService.approvePhrase(id);

        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deletePhrase(@RequestParam Long id) {

        phraseManagementAdminService.deletePhrase(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PatchMapping("reject")
    public ResponseEntity<PhrasePostObjectAdminDTO> rejectPhrase(@RequestParam Long id) {

        PhrasePostObjectAdminDTO res = phraseManagementAdminService.rejectPhrase(id);

        return ResponseEntity.ok().body(res);
    }

    @PatchMapping("pend")
    public ResponseEntity<PhrasePostObjectAdminDTO> pendPhrase(@RequestParam Long id) {

        PhrasePostObjectAdminDTO res = phraseManagementAdminService.pendPhrase(id);

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("get-analytics")
    public ResponseEntity<AnalyticsDTO> getAnalytics() {
        return ResponseEntity.ok().body(phraseManagementAdminService.getAnalytics());
    }



}

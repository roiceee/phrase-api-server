package com.roiceee.phraseapi.apikeymanagement.controller;

import com.roiceee.phraseapi.apikeymanagement.dto.UserApiKeyDTO;
import com.roiceee.phraseapi.apikeymanagement.service.ApiKeyService;
import com.roiceee.phraseapi.util.Origins;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {Origins.LOCAL, Origins.PROD})
@RequestMapping("apikey")
@AllArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping("create")
    public ResponseEntity<UserApiKeyDTO> createAPIKey(Authentication authentication) {


        return ResponseEntity.ok()
                .body(apiKeyService.createNewApiKey(authentication.getName()));
    }

    @GetMapping("get")
    public ResponseEntity<UserApiKeyDTO> getApiKey(Authentication authentication) {
        return ResponseEntity.ok().body(apiKeyService.getApiKey(authentication.getName()));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteApiKey(Authentication authentication) {
        apiKeyService.deleteApiKey(authentication.getName());
        return ResponseEntity.ok().body("API key deleted.");
    }
}


package com.roiceee.phraseapi.apikeymanagement.controllers;

import com.roiceee.phraseapi.apikeymanagement.services.ApiKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("apikey")
public class ApiKeyController {

    ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createAPIKey(Authentication authentication) {

        String apiKey = apiKeyService.createNewApiKey(authentication.getName());

        return ResponseEntity.ok().body(apiKey);
    }

    @GetMapping("get")
    public ResponseEntity<String> getApiKey() {

        return ResponseEntity.ok().body("Sample API Key");
    }
}


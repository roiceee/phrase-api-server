package com.roiceee.phraseapi.apikeymanagement.controllers;

import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
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
    public ResponseEntity<UserApiKeyModel> createAPIKey(Authentication authentication) {

        UserApiKeyModel userApiKeyModel = apiKeyService.createNewApiKey(authentication.getName());

        return ResponseEntity.ok()
                .body(userApiKeyModel);
    }
    @GetMapping("get")
    public ResponseEntity<UserApiKeyModel> getApiKey(Authentication authentication) {
        UserApiKeyModel userApiKeyModel = apiKeyService.getApiKey(authentication.getName());
        return ResponseEntity.ok().body(userApiKeyModel);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteApiKey(Authentication authentication) {
        apiKeyService.deleteApiKey(authentication.getName());
        return ResponseEntity.ok().body("API key deleted.");
    }
}


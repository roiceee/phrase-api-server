package com.roiceee.phraseapi.apikeymanagement.controllers;

import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.services.ApiKeyService;
import com.roiceee.phraseapi.services.ResourceControllerLimiterService;
import com.roiceee.phraseapi.util.Origins;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {Origins.LOCAL, Origins.PROD})
@RequestMapping("apikey")
public class ApiKeyController {

    ApiKeyService apiKeyService;
    ResourceControllerLimiterService resourceControllerLimiterService;

    public ApiKeyController(ApiKeyService apiKeyService, ResourceControllerLimiterService resourceControllerLimiterService) {
        this.apiKeyService = apiKeyService;
        this.resourceControllerLimiterService = resourceControllerLimiterService;
    }

    @PostMapping("create")
    public ResponseEntity<UserApiKeyModel> createAPIKey(Authentication authentication) {

        UserApiKeyModel userApiKeyModel = apiKeyService.createNewApiKey(authentication.getName());
        resourceControllerLimiterService.addExistingKeyIfAbsentInCache(userApiKeyModel.getApiKey());
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
        Optional<UserApiKeyModel> keyModel = apiKeyService.deleteApiKey(authentication.getName());
        keyModel.ifPresent(userApiKeyModel -> resourceControllerLimiterService.deleteFromCache(userApiKeyModel.getApiKey()));
        return ResponseEntity.ok().body("API key deleted.");
    }
}


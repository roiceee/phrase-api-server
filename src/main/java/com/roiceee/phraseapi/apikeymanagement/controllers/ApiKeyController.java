package com.roiceee.phraseapi.apikeymanagement.controllers;

import com.roiceee.phraseapi.apikeymanagement.models.ApiKeyResponseModel;
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
    public ResponseEntity<ApiKeyResponseModel> createAPIKey(Authentication authentication) {

        String apiKey = apiKeyService.createNewApiKey(authentication.getName());

        ApiKeyResponseModel responseModel = new ApiKeyResponseModel(apiKey);

        return ResponseEntity.ok()
                .body(responseModel);
    }

    @GetMapping("get")
    public ResponseEntity<String> getApiKey() {

        return ResponseEntity.ok().body("Sample API Key");
    }
}


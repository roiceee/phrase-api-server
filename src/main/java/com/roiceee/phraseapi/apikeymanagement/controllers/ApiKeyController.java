package com.roiceee.phraseapi.apikeymanagement.controllers;

import com.roiceee.phraseapi.apikeymanagement.services.ApiKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apikey")
public class ApiKeyController {

    ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createAPIKey() {

        return ResponseEntity.ok().body("Api Key Created");
    }

    @GetMapping("get")
    public ResponseEntity<String> getApiKey() {

        return ResponseEntity.ok().body("Sample API Key");
    }
}


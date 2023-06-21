package com.roiceee.phraseapi.apikeymanagement.controllers;

import com.roiceee.phraseapi.apikeymanagement.dtos.UserApiKeyDTO;
import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.services.ApiKeyService;
import com.roiceee.phraseapi.util.Origins;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = {Origins.LOCAL, Origins.PROD})
@RequestMapping("apikey")
@AllArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;
    private final ModelMapper modelMapper;

    @PostMapping("create")
    public ResponseEntity<UserApiKeyDTO> createAPIKey(Authentication authentication) {

        UserApiKeyModel userApiKeyModel = apiKeyService.createNewApiKey(authentication.getName());
        UserApiKeyDTO userApiKeyDTO = modelMapper.map(userApiKeyModel, UserApiKeyDTO.class);
        return ResponseEntity.ok()
                .body(userApiKeyDTO);
    }
    @GetMapping("get")
    public ResponseEntity<UserApiKeyDTO> getApiKey(Authentication authentication) {
        UserApiKeyModel userApiKeyModel = apiKeyService.getApiKey(authentication.getName());
        UserApiKeyDTO userApiKeyDTO = modelMapper.map(userApiKeyModel, UserApiKeyDTO.class);
        return ResponseEntity.ok().body(userApiKeyDTO);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteApiKey(Authentication authentication) {
        apiKeyService.deleteApiKey(authentication.getName());
        return ResponseEntity.ok().body("API key deleted.");
    }
}


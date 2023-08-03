package com.roiceee.phraseapi.apikeymanagement.dto;

import lombok.Data;

@Data
public class UserApiKeyDTO {
    private String apiKey;

    public UserApiKeyDTO(String apiKey) {
        this.apiKey = apiKey;
    }
}

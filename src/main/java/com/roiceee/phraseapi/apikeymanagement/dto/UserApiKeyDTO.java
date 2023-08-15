package com.roiceee.phraseapi.apikeymanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserApiKeyDTO {

    public UserApiKeyDTO(String apiKey) {
        this.apiKey = apiKey;
    }

    private String apiKey;

}

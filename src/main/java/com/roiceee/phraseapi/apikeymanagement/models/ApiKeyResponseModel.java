package com.roiceee.phraseapi.apikeymanagement.models;

import java.util.Objects;

public class ApiKeyResponseModel {
    private String apiKey;

    public ApiKeyResponseModel(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "ApiKeyResponseModel{" +
                "apiKey='" + apiKey + '\'' +
                '}';
    }
}

package com.roiceee.phraseapi.apikeymanagement.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_keys")
public class UserApiKeyModel {
    @Id
    @Column(name = "id")
    private String ID;

    @Column(name = "api_key")
    private String apiKey;


    public void setID(String id) {
        this.ID = id;
    }

    @JsonIgnore
    public String getID() {
        return ID;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKeys) {
        this.apiKey = apiKeys;
    }
}


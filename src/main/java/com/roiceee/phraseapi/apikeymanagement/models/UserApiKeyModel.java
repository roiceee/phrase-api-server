package com.roiceee.phraseapi.apikeymanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_keys")
public class UserApiKeyModel {
    @Id
    @Column(name = "id")
    private Long ID;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "api_key")
    private String apiKey;


    public void setID(Long id) {
        this.ID = id;
    }


    public Long getID() {
        return ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKeys) {
        this.apiKey = apiKeys;
    }
}


package com.roiceee.phraseapi.apikeymanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "api_keys")
@Getter
@Setter
public class UserApiKeyModel {

    public UserApiKeyModel() {
    }

    public UserApiKeyModel(String ID, String apiKey) {
        this.ID = ID;
        this.apiKey = apiKey;
    }


    @Id
    @Column(name = "id")
    private String ID;

    @Column(name = "api_key")
    private String apiKey;
}


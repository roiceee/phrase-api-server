package com.roiceee.phraseapi.apikeymanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "api_keys")
@Data
public class UserApiKeyModel {
    @Id
    @Column(name = "id")
    private String ID;

    @Column(name = "api_key")
    private String apiKey;
}


package com.roiceee.phraseapi.apikeymanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "api_keys")
@Getter
@Setter
@NoArgsConstructor
public class UserApiKeyModel {

    public UserApiKeyModel(String ID, String apiKey) {
        this.ID = ID;
        this.apiKey = apiKey;
    }


    @Id
    @Column(name = "id")
    private String ID;

    @Column(name = "api_key")
    private String apiKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserApiKeyModel that = (UserApiKeyModel) o;
        return Objects.equals(getID(), that.getID()) && Objects.equals(getApiKey(), that.getApiKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getApiKey());
    }
}


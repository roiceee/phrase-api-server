package com.roiceee.phraseapi.resourceapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "request_counter")
@Getter
@Setter
public class RequestCountModel {
    @Id
    @Column(name = "api_key")
    private UUID apiKey;

    @Column(name = "count")
    private Long count;

    @Column(name = "owner")
    private String owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestCountModel model = (RequestCountModel) o;
        return Objects.equals(getApiKey(), model.getApiKey()) && Objects.equals(getCount(), model.getCount()) && Objects.equals(getOwner(), model.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApiKey(), getCount(), getOwner());
    }
}

package com.roiceee.phraseapi.resourceapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "request_counter")
@Data
public class RequestCountModel {
    @Id
    @Column(name = "api_key")
    private UUID apiKey;

    @Column(name = "count")
    private Long count;

    @Column(name = "owner")
    private String owner;

}

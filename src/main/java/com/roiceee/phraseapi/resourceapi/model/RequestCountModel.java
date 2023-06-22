package com.roiceee.phraseapi.resourceapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "request_count")
@Data
public class RequestCountModel {
    @Id
    @Column(name = "request_number")
    private Long requestNumber;
    @Column(name = "api_key")
    private String apiKey;

}

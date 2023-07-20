package com.roiceee.phraseapi.resourceapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "quotes")
@Data
public class QuoteModel extends Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private long ID;

    @Column(name = "author")
    private String author;

    @Column(name = "phrase")
    private String phrase;

    @Column(name = "phrase_management_id")
    private Long phraseManagementID;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}

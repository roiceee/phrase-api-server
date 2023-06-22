package com.roiceee.phraseapi.resourceapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "jokes")
@Data

public class JokeModel extends Phrase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long ID;

    @Column(name = "author")
    private String author;

    @Column(name = "phrase")
    private String phrase;

    @Column(name = "phrase_management_id")
    private long phrasemanagementID;
    @JsonIgnore
    public long getID() {
        return ID;
    }
}

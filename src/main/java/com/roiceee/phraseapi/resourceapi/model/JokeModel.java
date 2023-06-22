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
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long ID;
    private String author;
    private String phrase;
    @JsonIgnore
    public long getID() {
        return ID;
    }
}

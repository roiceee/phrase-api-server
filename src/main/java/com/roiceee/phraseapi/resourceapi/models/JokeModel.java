package com.roiceee.phraseapi.resourceapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "jokes")
@Data
public class JokeModel extends Phrase {

    @Id
    private long ID;
    private String phrase;


    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }


    public String getPhrase() {
        return phrase;
    }


}

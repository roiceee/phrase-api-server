package com.roiceee.phraseapi.resourceapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "jokes")
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

    @JsonIgnore
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JokeModel jokeModel = (JokeModel) o;
        return getID() == jokeModel.getID() && Objects.equals(phrase, jokeModel.phrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), phrase);
    }
}

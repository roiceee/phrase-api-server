package com.roiceee.quotejokeapi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class JokeModel implements Phrase {

    @Id
    private long ID;
    private String phrase;

    @Override
    public String getPhrase() {
        return phrase;
    }


    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JokeModel jokeModel = (JokeModel) o;
        return ID == jokeModel.ID && Objects.equals(getPhrase(), jokeModel.getPhrase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, getPhrase());
    }

}

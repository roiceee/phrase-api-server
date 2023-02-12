package com.roiceee.quotejokeapi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "jokes")
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
        return getID() == jokeModel.getID() && Objects.equals(getPhrase(), jokeModel.getPhrase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getPhrase());
    }
}

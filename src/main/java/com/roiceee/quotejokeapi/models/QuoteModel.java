package com.roiceee.quotejokeapi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class QuoteModel implements Phrase{

    @Id
    private long ID;
    private String author;
    private String phrase;



    @Override
    public String getPhrase() {
        return phrase;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPhrase(String quote) {
        this.phrase = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteModel that = (QuoteModel) o;
        return getID() == that.getID() && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getPhrase(),
                that.getPhrase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getAuthor(), getPhrase());
    }
}

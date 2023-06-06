package com.roiceee.phraseapi.phrasemanagement.dto;


import java.util.Objects;

public class PhrasePostObjectDTO {

    private long id;

    private String type;

    private String author;

    private String phrase;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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
        PhrasePostObjectDTO that = (PhrasePostObjectDTO) o;
        return getId() == that.getId() && Objects.equals(getType(), that.getType()) && Objects.equals(getAuthor()
                , that.getAuthor()) && Objects.equals(getPhrase(), that.getPhrase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getAuthor(), getPhrase());
    }

    @Override
    public String toString() {
        return "PhrasePostObjectDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", phrase='" + phrase + '\'' +
                '}';
    }
}

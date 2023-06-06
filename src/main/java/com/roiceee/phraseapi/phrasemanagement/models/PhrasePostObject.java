package com.roiceee.phraseapi.phrasemanagement.models;

import com.roiceee.phraseapi.resourceapi.models.Phrase;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "phrase_management")
public class PhrasePostObject extends Phrase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "type")
    private String type;

    @Column(name = "author")
    private String author;
    @Column(name = "phrase")
    private String phrase;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String getPhrase() {
        return phrase;
    }

    @Override
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhrasePostObject that = (PhrasePostObject) o;
        return getId() == that.getId() && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getType(), that.getType()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getPhrase(), that.getPhrase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getType(), getAuthor(), getPhrase());
    }

    @Override
    public String toString() {
        return "PhrasePostObject{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", phrase='" + phrase + '\'' +
                '}';
    }
}

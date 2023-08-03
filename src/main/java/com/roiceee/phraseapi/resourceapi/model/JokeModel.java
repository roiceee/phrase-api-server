package com.roiceee.phraseapi.resourceapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "jokes")
@Getter
@Setter
@NoArgsConstructor
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
    private Long phraseManagementID;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());


    public JokeModel(String author, String phrase) {
        this.author = author;
        this.phrase = phrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JokeModel jokeModel = (JokeModel) o;
        return getID() == jokeModel.getID() && Objects.equals(getAuthor(), jokeModel.getAuthor()) && Objects.equals(getPhrase(), jokeModel.getPhrase()) && Objects.equals(getPhraseManagementID(), jokeModel.getPhraseManagementID()) && Objects.equals(getTimestamp(), jokeModel.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getAuthor(), getPhrase(), getPhraseManagementID(), getTimestamp());
    }
}

package com.roiceee.phraseapi.resourceapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;



@Entity
@Table(name = "quotes")
@Getter
@Setter
@NoArgsConstructor
public class QuoteModel extends Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private long ID;

    @Column(name = "author")
    private String author;

    @Column(name = "phrase")
    private String phrase;

    @Column(name = "phrase_management_id")
    private Long phraseManagementID;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public QuoteModel(String author, String phrase) {
        this.author = author;
        this.phrase = phrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteModel that = (QuoteModel) o;
        return getID() == that.getID() && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getPhrase(), that.getPhrase()) && Objects.equals(getPhraseManagementID(), that.getPhraseManagementID()) && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getAuthor(), getPhrase(), getPhraseManagementID(), getTimestamp());
    }
}

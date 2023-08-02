package com.roiceee.phraseapi.phrasemanagement.model;

import com.roiceee.phraseapi.resourceapi.model.Phrase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;
import java.util.Objects;



@Entity
@Table(name = "phrase_management")
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
public class PhrasePostObject extends Phrase {

    public PhrasePostObject(String userId, String type, String author, String phrase) {
        this.userId = userId;
        this.type = type;
        this.author = author;
        this.phrase = phrase;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "type")
    private String type;

    @Column(name = "author")
    private String author;

    @Column(name = "phrase")
    private String phrase;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(255) default 'PENDING'")
    private Status status = Status.PENDING;

    @Column(name = "date_submitted", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateSubmitted = new Timestamp(System.currentTimeMillis());

    @Column(name = "date_modified_by_admin")
    private Timestamp dateModifiedByAdmin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhrasePostObject that = (PhrasePostObject) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getType(), that.getType()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getPhrase(), that.getPhrase()) && getStatus() == that.getStatus() && Objects.equals(getDateSubmitted(), that.getDateSubmitted()) && Objects.equals(getDateModifiedByAdmin(), that.getDateModifiedByAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getType(), getAuthor(), getPhrase(), getStatus(), getDateSubmitted(), getDateModifiedByAdmin());
    }
}

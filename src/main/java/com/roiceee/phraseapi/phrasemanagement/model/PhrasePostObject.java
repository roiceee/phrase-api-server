package com.roiceee.phraseapi.phrasemanagement.model;

import com.roiceee.phraseapi.resourceapi.model.Phrase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "phrase_management")
@Data
@NoArgsConstructor
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

}

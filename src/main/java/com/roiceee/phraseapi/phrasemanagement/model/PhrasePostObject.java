package com.roiceee.phraseapi.phrasemanagement.model;

import com.roiceee.phraseapi.resourceapi.model.Phrase;
import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "phrase_management")
@Data
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

}

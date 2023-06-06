package com.roiceee.phraseapi.phrasemanagement.models;

import com.roiceee.phraseapi.resourceapi.models.Phrase;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "phrase_management")
@Getter
@Setter
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

    @Column(name = "status")
    private String status;

}

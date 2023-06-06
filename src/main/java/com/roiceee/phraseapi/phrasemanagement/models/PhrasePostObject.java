package com.roiceee.phraseapi.phrasemanagement.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.roiceee.phraseapi.resourceapi.models.Phrase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.TypeAlias;

@Entity
@Table(name = "phrase_management")
@Getter
@Setter
@ToString
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

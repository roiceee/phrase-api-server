package com.roiceee.phraseapi.resourceapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "quotes")
@Data
public class QuoteModel extends Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long ID;

    private String author;
    private String phrase;
    @JsonIgnore
    public long getID() {
        return ID;
    }
}

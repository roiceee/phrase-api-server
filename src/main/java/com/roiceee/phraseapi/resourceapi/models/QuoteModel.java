package com.roiceee.phraseapi.resourceapi.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "quotes")
@Data
public class QuoteModel extends Phrase {

    @Id

    private long ID;

    private String author;
    private String phrase;
    @JsonIgnore
    public long getID() {
        return ID;
    }
}

package com.roiceee.phraseapi.phrasemanagement.dto;


import com.roiceee.phraseapi.phrasemanagement.model.Status;
import lombok.Data;

@Data
public class PhrasePostObjectDTO {

    private long id;

    private String type;

    private String author;

    private String phrase;

    private Status status;
}

package com.roiceee.phraseapi.phrasemanagement.dto;


import com.roiceee.phraseapi.phrasemanagement.model.Status;
import lombok.Data;

@Data
public class PhrasePostObjectUserDTO {

    private Long id;

    private String type;

    private String author;

    private String phrase;

    private Status status;

    private String dateSubmitted;
}

package com.roiceee.phraseapi.phrasemanagement.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@EqualsAndHashCode
@Getter
@Setter
public class PhrasePostObjectDTO {

    private long id;

    private String type;

    private String author;

    private String phrase;

    private String status;
}

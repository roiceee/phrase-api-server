package com.roiceee.phraseapi.resourceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhraseDTO {
    private String author;
    private String phrase;
    private String type;
}

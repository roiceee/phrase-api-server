package com.roiceee.phraseapi.resourceapi.model;


import lombok.Data;

@Data
public abstract class Phrase {

   protected String phrase;

   public abstract String getPhrase();

   public abstract void setPhrase(String phrase);
}
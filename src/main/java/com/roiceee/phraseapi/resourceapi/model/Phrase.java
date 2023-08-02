package com.roiceee.phraseapi.resourceapi.model;


import java.util.Objects;

public abstract class Phrase {

   protected String phrase;

   public abstract String getPhrase();

   public abstract void setPhrase(String phrase);

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Phrase phrase1 = (Phrase) o;
      return Objects.equals(getPhrase(), phrase1.getPhrase());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getPhrase());
   }
}

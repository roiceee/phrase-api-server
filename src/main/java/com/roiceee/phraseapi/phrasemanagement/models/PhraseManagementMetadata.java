package com.roiceee.phraseapi.phrasemanagement.models;

public class PhraseManagementMetadata {
    private int maxPhrases;
    private int currentPhrases;

    public PhraseManagementMetadata(int maxPhrases, int currentPhrases) {
        this.maxPhrases = maxPhrases;
        this.currentPhrases = currentPhrases;
    }

    public int getCurrentPhrases() {
        return currentPhrases;
    }

    public void setCurrentPhrases(int currentPhrases) {
        this.currentPhrases = currentPhrases;
    }

    public int getMaxPhrases() {
        return maxPhrases;
    }

    public void setMaxPhrases(int maxPhrases) {
        this.maxPhrases = maxPhrases;
    }
}

package com.roiceee.phraseapi.phrasemanagement.dto;

import lombok.Data;

@Data
public class AnalyticsDTO {
    private Long totalPhrases;
    private Long quotes;
    private Long jokes;
    private Long userDefinedPhrases;
    private Long approvedPhrases;
    private Long rejectedPhrases;
    private Long pendingPhrases;
    private Long requests;
    private Long apiKeys;
}
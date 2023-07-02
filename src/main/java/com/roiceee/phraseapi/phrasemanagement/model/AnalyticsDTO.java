package com.roiceee.phraseapi.phrasemanagement.model;

import lombok.Data;

@Data
public class AnalyticsDTO {
    private Long total;
    private Long quotes;
    private Long jokes;
    private Long requests;
    private Long apiKeys;
}

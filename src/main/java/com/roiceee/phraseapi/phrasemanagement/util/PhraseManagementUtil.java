package com.roiceee.phraseapi.phrasemanagement.util;

import java.sql.Timestamp;

public class PhraseManagementUtil {
    public final static int MAX_PHRASES = 20;
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}

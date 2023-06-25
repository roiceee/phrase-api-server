package com.roiceee.phraseapi.phrasemanagement.model;

public enum Status {
    PENDING("PENDING"),
    REJECTED("REJECTED"),
    APPROVED("APPROVED");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }
}

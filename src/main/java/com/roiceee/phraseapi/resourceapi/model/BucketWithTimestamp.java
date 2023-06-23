package com.roiceee.phraseapi.resourceapi.model;

import io.github.bucket4j.Bucket;



public class BucketWithTimestamp {
    private Bucket bucket;
    private long timestamp;

    public BucketWithTimestamp(Bucket bucket, long timestamp) {
        this.bucket = bucket;
        this.timestamp = timestamp;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

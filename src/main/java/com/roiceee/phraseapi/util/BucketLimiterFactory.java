package com.roiceee.phraseapi.util;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import java.time.Duration;

public class BucketLimiterFactory {
    public static Bucket createBucket(int requestLimit, int refreshLimitInMinutes) {
        Bandwidth bandwidth = Bandwidth.classic(requestLimit, Refill.intervally(requestLimit,
                Duration.ofMinutes(refreshLimitInMinutes)));
        return Bucket.builder().addLimit(bandwidth).build();
    }
}

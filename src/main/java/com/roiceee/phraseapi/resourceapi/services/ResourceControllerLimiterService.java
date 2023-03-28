package com.roiceee.phraseapi.resourceapi.services;

import com.roiceee.phraseapi.resourceapi.exceptions.TooManyRequestsException;
import com.roiceee.phraseapi.resourceapi.models.BucketWithTimestamp;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ResourceControllerLimiterService {
    ConcurrentHashMap<String, BucketWithTimestamp> bucketCache;

     Logger logger = LoggerFactory.getLogger(ResourceControllerLimiterService.class);

    public ResourceControllerLimiterService() {
        logger.info("Instantiated");
        cleanCacheWithInterval();
    }


    public void addExistingKeyIfAbsentInCache(String apiKey) {
        BucketWithTimestamp bucketWithTimestamp = new BucketWithTimestamp(this.newBucket(),
                this.getCurrentTimestamp());
        bucketCache.putIfAbsent(apiKey, bucketWithTimestamp);
    }

    public void consumeOne(String apiKey) {
        BucketWithTimestamp bucket = bucketCache.get(apiKey);
        bucket.setTimestamp(this.getCurrentTimestamp());
        if (bucket.getBucket().tryConsume(1)) {
            return;
        }
        throw new TooManyRequestsException();
    }

    private void clearUnusedKeysFromCache() {
        long HOURS_LAST_INVOKED_BEFORE_DELETE = 3;
        long removedItems = 0;
        Iterator<Map.Entry<String, BucketWithTimestamp>> iterator = bucketCache.entrySet().iterator();
        logger.info("Clean action starting...");
        while (iterator.hasNext()) {
            Map.Entry<String, BucketWithTimestamp> entry = iterator.next();
            long lastAccessed = entry.getValue().getTimestamp();
            long difference = this.getCurrentTimestamp() - lastAccessed;
            if (this.hoursToMilliseconds(HOURS_LAST_INVOKED_BEFORE_DELETE) > difference) {
                continue;
            }
            removedItems++;
            iterator.remove();
        }
        logger.info("Removed " + removedItems + ".");
    }

    private Bucket newBucket() {
        int REFRESH_INTERVAL_IN_MINUTES = 1;
        int REQUEST_LIMIT = 100;
        Bandwidth bandwidth = Bandwidth.classic(REQUEST_LIMIT, Refill.intervally(REQUEST_LIMIT,
                Duration.ofMinutes(REFRESH_INTERVAL_IN_MINUTES)));
        return Bucket.builder().addLimit(bandwidth).build();
    }
    private long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    private void cleanCacheWithInterval() {
        long INTERVAL_IN_HOURS = 6;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                clearUnusedKeysFromCache();
            }
        }, new Date().getTime() + this.hoursToMilliseconds(6), this.hoursToMilliseconds(INTERVAL_IN_HOURS));
    }

    private long hoursToMilliseconds(long hours) {
        return hours * 60 * 60 * 1000;
    }
}

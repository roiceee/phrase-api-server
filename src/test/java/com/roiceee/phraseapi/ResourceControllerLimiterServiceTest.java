package com.roiceee.phraseapi;

import com.roiceee.phraseapi.resourceapi.exceptions.TooManyRequestsException;
import com.roiceee.phraseapi.resourceapi.models.BucketWithTimestamp;
import com.roiceee.phraseapi.resourceapi.services.ResourceControllerLimiterService;
import com.roiceee.phraseapi.util.BucketLimiterFactory;
import com.roiceee.phraseapi.util.Conversions;
import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResourceControllerLimiterServiceTest {

    @InjectMocks
    private ResourceControllerLimiterService service;

    @Mock
    private ConcurrentHashMap<String, BucketWithTimestamp> bucketCache = new ConcurrentHashMap<>();


    @Test
    public void testAddExistingKeyIfAbsentInCache() {
        service.addExistingKeyIfAbsentInCache("api_key");
        verify(bucketCache).putIfAbsent(eq("api_key"), any(BucketWithTimestamp.class));
    }

    @Test
    public void testConsumeOne() {
        String apiKey = "testConsumeOne";
        when(bucketCache.get(apiKey)).thenReturn(createBucketWithTimestamp(2));
        service.addExistingKeyIfAbsentInCache(apiKey);
        service.consumeOne(apiKey);
        Assertions.assertEquals(bucketCache.get(apiKey).getBucket().getAvailableTokens(), 1);
    }

    @Test
    public void testConsumeOneTooManyRequests() {
        String apiKey = "testConsumeOneTooManyRequests";

        when(bucketCache.get(apiKey)).thenReturn(createBucketWithTimestamp());
        service.getBucketCache().get(apiKey);
        Assertions.assertThrows(TooManyRequestsException.class, () -> {
            for (int i = 0; i < 120; i++) {
                service.consumeOne(apiKey);
            }
        });
    }

    @Test
    public void testClearUnusedKeysFromCacheIfDeletes() {
        int initialSize = service.getBucketCache().size();
        String apiKey = "testClearUnusedKeysFromCacheIfDeletes";
        service.getBucketCache().putIfAbsent(apiKey, createBucketWithTimestamp());
        service.clearUnusedKeysFromCache();
        Assertions.assertEquals(initialSize, service.getBucketCache().size());

    }

    private BucketWithTimestamp createBucketWithTimestamp() {
        return createBucketWithTimestamp(100);
    }
    private BucketWithTimestamp createBucketWithTimestamp(int requestLimit) {
        Bucket bucket = BucketLimiterFactory.createBucket(requestLimit, 1);
        return new BucketWithTimestamp(bucket,
                Conversions.hoursToMilliseconds(System.currentTimeMillis()) - Conversions.hoursToMilliseconds(3));
    }

}

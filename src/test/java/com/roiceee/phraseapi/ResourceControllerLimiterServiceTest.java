package com.roiceee.phraseapi;

import com.roiceee.phraseapi.resourceapi.model.BucketWithTimestamp;
import com.roiceee.phraseapi.resourceapi.service.ResourceControllerLimiterService;
import com.roiceee.phraseapi.util.BucketLimiterFactory;
import com.roiceee.phraseapi.util.Conversions;
import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ConcurrentHashMap;

@ExtendWith(MockitoExtension.class)
public class ResourceControllerLimiterServiceTest {

    @InjectMocks
    private ResourceControllerLimiterService service;

    @Mock
    private ConcurrentHashMap<String, BucketWithTimestamp> bucketCache;


//    @Test
//    public void testAddExistingKeyIfAbsentInCache() {
//        service.addExistingKeyIfAbsentInCache("api_key");
//        verify(bucketCache).putIfAbsent(eq("api_key"), any(BucketWithTimestamp.class));
//    }
//
//    @Test
//    public void testConsumeOne() {
//        String apiKey = "testConsumeOne";
//        when(bucketCache.get(apiKey)).thenReturn(createBucketWithTimestamp(2, 2));
//        service.addExistingKeyIfAbsentInCache(apiKey);
//        service.consumeOne(apiKey);
//        Assertions.assertEquals(bucketCache.get(apiKey).getBucket().getAvailableTokens(), 1);
//    }
//
//    @Test
//    public void testConsumeOneTooManyRequests() {
//        String apiKey = "testConsumeOneTooManyRequests";
//
//        when(bucketCache.get(apiKey)).thenReturn(createBucketWithTimestamp());
//        service.getBucketCache().get(apiKey);
//        Assertions.assertThrows(TooManyRequestsException.class, () -> {
//            for (int i = 0; i < 120; i++) {
//                service.consumeOne(apiKey);
//            }
//        });
//    }
//
//    @Test
//    public void testClearUnusedKeysFromCacheIfDeletes() {
//        int initialSize = service.getBucketCache().size();
//        String apiKey = "testClearUnusedKeysFromCacheIfDeletes";
//
//        HashMap<String, BucketWithTimestamp> hashMap = new HashMap<>();
//        hashMap.put(apiKey, createBucketWithTimestamp(3));
//        Set<Map.Entry<String, BucketWithTimestamp>> entries = hashMap.entrySet();
//        when(bucketCache.entrySet()).thenReturn(entries);
//        service.clearUnusedKeysFromCache();
//
//        Assertions.assertEquals(initialSize, entries.size());
//
//    }
//
//    @Test
//    public void testClearUnusedKeysFromCacheIfPersistsActiveKeys() {
//        int initialSize = service.getBucketCache().size();
//        String apiKey = "testClearUnusedKeysFromCacheIfPersistsActiveKeys";
//
//        HashMap<String, BucketWithTimestamp> hashMap = new HashMap<>();
//        hashMap.put(apiKey, createBucketWithTimestamp(1));
//
//        Set<Map.Entry<String, BucketWithTimestamp>> entries = hashMap.entrySet();
//        when(bucketCache.entrySet()).thenReturn(entries);
//        service.clearUnusedKeysFromCache();
//        Assertions.assertEquals(initialSize + 1, entries.size());
//    }

    private BucketWithTimestamp createBucketWithTimestamp() {
        return createBucketWithTimestamp(1, 10);
    }

    private BucketWithTimestamp createBucketWithTimestamp(int hoursBeforeNow) {
        return createBucketWithTimestamp(hoursBeforeNow, 10);
    }
    private BucketWithTimestamp createBucketWithTimestamp(int hoursBeforeNow, int requestLimit) {
        Bucket bucket = BucketLimiterFactory.createBucket(requestLimit, 1);
        return new BucketWithTimestamp(bucket,
                System.currentTimeMillis() - Conversions.hoursToMilliseconds(hoursBeforeNow));
    }

}

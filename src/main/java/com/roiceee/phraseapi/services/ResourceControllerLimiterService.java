package com.roiceee.phraseapi.services;

import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.repositories.ApiKeyRepository;
import com.roiceee.phraseapi.resourceapi.exceptions.TooManyRequestsException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ResourceControllerLimiterService {
    ApiKeyRepository apiKeyRepository;
    ConcurrentHashMap<String, Bucket> bucketCache;

    public ResourceControllerLimiterService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
        initializeCache();
    }

    public void initializeCache() {

        ConcurrentHashMap<String, Bucket> bucketMap = new ConcurrentHashMap<>();

        List<UserApiKeyModel> userApiKeyModels = apiKeyRepository.getAll();
        for (UserApiKeyModel model : userApiKeyModels) {
            String key = model.getApiKey();
            bucketMap.putIfAbsent(key, this.newBucket());
        }
        this.bucketCache = bucketMap;
    }

    public Bucket newBucket() {
        int REFRESH_INTERVAL_IN_MINUTES = 1;
        int REQUEST_LIMIT = 100;
        Bandwidth bandwidth = Bandwidth.classic(REQUEST_LIMIT, Refill.intervally(REQUEST_LIMIT,
                Duration.ofMinutes(REFRESH_INTERVAL_IN_MINUTES)));
        return Bucket.builder().addLimit(bandwidth).build();
    }

    public void addExistingKeyIfAbsentInCache(String apiKey) {
        bucketCache.putIfAbsent(apiKey, this.newBucket());
    }

    public void consumeOne(String apiKey) {
        Bucket bucket = bucketCache.get(apiKey);
        if (bucket.tryConsume(1)) {
            return;
        }
        throw new TooManyRequestsException();
    }

    public void deleteFromCache(String apiKey) {
        bucketCache.remove(apiKey);
    }
}

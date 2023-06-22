package com.roiceee.phraseapi.resourceapi.config;

import com.roiceee.phraseapi.resourceapi.service.ResourceControllerLimiterService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    private final ResourceControllerLimiterService resourceControllerLimiterService;

    public ScheduledTasks(ResourceControllerLimiterService resourceControllerLimiterService) {
        this.resourceControllerLimiterService = resourceControllerLimiterService;
    }

    //delay is 6 hours = 21600000 milliseconds
    @Async
    @Scheduled(initialDelay = 21600000, fixedDelay = 21600000)
    public void clearResourceControllerLimiterServiceCache() {
        resourceControllerLimiterService.clearUnusedKeysFromCache();
    }
}

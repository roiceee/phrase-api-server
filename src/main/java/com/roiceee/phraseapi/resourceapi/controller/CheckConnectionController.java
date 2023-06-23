package com.roiceee.phraseapi.resourceapi.controller;


import com.roiceee.phraseapi.resourceapi.exception.TooManyRequestsException;
import com.roiceee.phraseapi.util.BucketLimiterFactory;
import io.github.bucket4j.Bucket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "check")
@CrossOrigin
public class CheckConnectionController {

    private final Bucket bucket;

    public CheckConnectionController() {
        final int REQUEST_LIMIT = 1000;
        final int REFRESH_PER_MINUTES = 1;
        this.bucket = BucketLimiterFactory.createBucket(REQUEST_LIMIT, REFRESH_PER_MINUTES);
    }

    @GetMapping
    public ResponseEntity<Object> returnOK() {
        if (!bucket.tryConsume(1)) {
            throw new TooManyRequestsException();
        }
        return ResponseEntity.ok().build();
    }
}

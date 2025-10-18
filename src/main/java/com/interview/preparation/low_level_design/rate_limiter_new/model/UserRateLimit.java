package com.interview.preparation.low_level_design.rate_limiter_new.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRateLimit {
    private String userId;

    //Fixed Window Counter and Sliding Window Log specifics:
    private long limit;
    private long windowSeconds;

    // Token bucket specifics:
    private long bucketCapacity;
    private double refillPerSecond;

    public UserRateLimit(String userId, long limit, long windowSeconds, long bucketCapacity) {
        this.userId = userId;
        this.limit = limit;
        this.windowSeconds = windowSeconds;
        this.bucketCapacity = bucketCapacity; // default
        this.refillPerSecond = (double) limit / windowSeconds; // default if needed
    }
}

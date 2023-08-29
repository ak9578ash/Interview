package com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter;

import com.interview.preparation.low_level_design.rate_limiter.RateLimiter;
import lombok.Getter;

@Getter
public class TokenBucket implements RateLimiter {
    private final long windowTime; // in seconds
    private final long noOfRequest;
    private int token;
    private long lastUpdatedAt;


    public TokenBucket(long windowTime, long noOfRequest) {
        this.token = (int) (noOfRequest / windowTime);
        this.lastUpdatedAt = 0L;
        this.windowTime = windowTime;
        this.noOfRequest = noOfRequest;
    }

    @Override
    public boolean grantAccess() {
        Long currentTimeOfRequest = System.currentTimeMillis();

        if ((currentTimeOfRequest - lastUpdatedAt) / 1000 < 1 && token > 0) {
            // request is passed
            token = token - 1;
            lastUpdatedAt = currentTimeOfRequest;
            return true;
        }

        if ((currentTimeOfRequest - lastUpdatedAt) / 1000 >= 1) {
            // request is passed
            lastUpdatedAt = currentTimeOfRequest;
            token = (int) (noOfRequest / windowTime);
            token = token - 1;
            return true;
        }
        return false;
    }
}

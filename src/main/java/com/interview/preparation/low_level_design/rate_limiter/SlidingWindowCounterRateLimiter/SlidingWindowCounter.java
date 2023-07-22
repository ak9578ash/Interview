package com.interview.preparation.low_level_design.rate_limiter.SlidingWindowCounterRateLimiter;

import com.interview.preparation.low_level_design.rate_limiter.RateLimiter;

public class SlidingWindowCounter implements RateLimiter {
    @Override
    public boolean grantAccess() {
        return false;
    }
}

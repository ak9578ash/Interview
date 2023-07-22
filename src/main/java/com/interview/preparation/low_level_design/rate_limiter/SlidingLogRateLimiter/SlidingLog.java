package com.interview.preparation.low_level_design.rate_limiter.SlidingLogRateLimiter;

import com.interview.preparation.low_level_design.rate_limiter.RateLimiter;

public class SlidingLog implements RateLimiter {
    @Override
    public boolean grantAccess() {
        return false;
    }
}

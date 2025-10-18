package com.interview.preparation.low_level_design.rate_limiter_new;

import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;

public interface RateLimiterStrategy {
    boolean allowRequest(String userId);
    void onUserConfigChange(String userId, UserRateLimit config);
}

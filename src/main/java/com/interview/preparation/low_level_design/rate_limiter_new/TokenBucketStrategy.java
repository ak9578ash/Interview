package com.interview.preparation.low_level_design.rate_limiter_new;

import com.interview.preparation.low_level_design.rate_limiter_new.model.BucketInfo;
import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;
import com.interview.preparation.low_level_design.rate_limiter_new.repository.UserRateLimitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@AllArgsConstructor
@Slf4j
public class TokenBucketStrategy implements RateLimiterStrategy {
    private final ConcurrentMap<String, BucketInfo> buckets = new ConcurrentHashMap<>();
    private final UserRateLimitRepository userRateLimitRepository;

    @Override
    public boolean allowRequest(String userId) {
        UserRateLimit cfg = userRateLimitRepository.findById(userId).orElse(null);
        if (cfg == null) {
            log.error("User not found for {}", userId);
            return false; // unknown user => reject (or treat as default)
        }

        BucketInfo b = buckets.computeIfAbsent(userId, id -> {
            BucketInfo nb = new BucketInfo(cfg.getBucketCapacity(), System.currentTimeMillis());
            return nb;
        });

        long now = System.currentTimeMillis();
        long elapsedMillis = now - b.getLastRefill();
        if (elapsedMillis > 0) {
            double tokensToAdd = (elapsedMillis / 1000.0) * cfg.getRefillPerSecond();
            b.setTokens(Math.min(cfg.getBucketCapacity(), b.getTokens() + tokensToAdd));
            b.setLastRefill(now);
        }
        if (b.tokens >= 1.0) {
            b.tokens -= 1.0;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onUserConfigChange(String userId, UserRateLimit config) {
        buckets.remove(userId);
    }
}

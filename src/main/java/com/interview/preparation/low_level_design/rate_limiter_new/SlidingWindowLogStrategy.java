package com.interview.preparation.low_level_design.rate_limiter_new;

import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;
import com.interview.preparation.low_level_design.rate_limiter_new.repository.UserRateLimitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;

@AllArgsConstructor
@Slf4j
public class SlidingWindowLogStrategy implements RateLimiterStrategy {
    private final ConcurrentMap<String, ConcurrentLinkedDeque<Long>> logs = new ConcurrentHashMap<>();
    private final UserRateLimitRepository userRateLimitRepository;

    @Override
    public boolean allowRequest(String userId) {
        UserRateLimit cfg = userRateLimitRepository.findById(userId).orElse(null);
        if (cfg == null) {
            log.error("User not found for {}", userId);
            return false; // unknown user => reject (or treat as default)
        }

        long windowSeconds = Math.max(1, cfg.getWindowSeconds());
        long now = Instant.now().getEpochSecond();
        long cutoff = now - windowSeconds;

        ConcurrentLinkedDeque<Long> deque = logs.computeIfAbsent(userId, k -> new ConcurrentLinkedDeque<>());
        // Prune old timestamps
        while (true) {
            Long head = deque.peekFirst();
            if (head == null || head >= cutoff) {
                break;
            }
            deque.pollFirst();
        }

        if (deque.size() < cfg.getLimit()) {
            deque.addLast(now);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onUserConfigChange(String userId, UserRateLimit config) {
        logs.remove(userId);
    }
}

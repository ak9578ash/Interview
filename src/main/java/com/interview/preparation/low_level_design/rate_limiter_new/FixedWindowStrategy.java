package com.interview.preparation.low_level_design.rate_limiter_new;

import com.interview.preparation.low_level_design.rate_limiter_new.model.FixedWindowInfo;
import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;
import com.interview.preparation.low_level_design.rate_limiter_new.repository.UserRateLimitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Slf4j
@Component
public class FixedWindowStrategy implements RateLimiterStrategy {
    private final Map<String, FixedWindowInfo> state = new ConcurrentHashMap<>();
    private final UserRateLimitRepository userRateLimitRepository;

    @Override
    public boolean allowRequest(String userId) {
        UserRateLimit cfg = userRateLimitRepository.findById(userId).orElse(null);
        if (cfg == null) {
            log.error("User not found for {}", userId);
            return false; // unknown user => reject (or treat as default)
        }

        long windowSeconds = Math.max(1, cfg.getWindowSeconds());
        long nowSec = Instant.now().getEpochSecond();
        long currentWindowStart = (nowSec / windowSeconds) * windowSeconds;

        FixedWindowInfo win = state.computeIfAbsent(userId, id -> {
            return new FixedWindowInfo(currentWindowStart, 0);
        });

        long observedWindow = win.getWindowStartEpochSec();
        // If we’ve moved into a new window, reset the counter
        if (observedWindow < currentWindowStart) {
            win.setWindowStartEpochSec(currentWindowStart);
            win.setCounter(0);
        }


        // Check if the user is still within the limit
        if (win.getCounter() <= cfg.getLimit()) {
            return true;
        } else {
            // Exceeded the limit, revert the increment
            win.setCounter(win.getCounter() - 1);
            return false;
        }

        // Synchronize on the per-user object so only one thread updates this user's counters at a time.
//        synchronized (win) {
//            long observedWindow = win.getWindowStartEpochSec();
//            // If new time window has started, reset counter and windowStart atomically
//            if (observedWindow < currentWindowStart) {
//                win.setWindowStartEpochSec(currentWindowStart);
//                win.setCounter(0);
//            }
//
//            // If within allowed limit, increment and allow
//            if (win.getCounter() < cfg.getLimit()) {
//                win.setCounter(win.getCounter() + 1);
//                return true;
//            } else {
//                // Already at or above limit — reject.
//                return false;
//            }
//        }

        /*

                  For distributed/high-throughput:
                  1.Use Redis with atomic commands/Lua scripts or use a DB with OCC + retries.
                  2.If you use DB + OCC, be aware of retry/backoff logic and test under contention.
                  3.For token-bucket style, Redis-based atomic scripts are typical.
         */
    }

    @Override
    public void onUserConfigChange(String userId, UserRateLimit config) {
        state.remove(userId);
    }
}

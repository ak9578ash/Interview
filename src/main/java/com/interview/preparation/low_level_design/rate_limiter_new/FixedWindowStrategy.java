package com.interview.preparation.low_level_design.rate_limiter_new;

import com.interview.preparation.low_level_design.rate_limiter_new.model.FixedWindowInfo;
import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;
import com.interview.preparation.low_level_design.rate_limiter_new.repository.UserRateLimitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;

@AllArgsConstructor
@Slf4j
@Component
public class FixedWindowStrategy implements RateLimiterStrategy {
    private final HashMap<String, FixedWindowInfo> state = new LinkedHashMap<>();
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

        // Increment the counter for this window
        win.setCounter(win.getCounter() + 1);

        // Check if the user is still within the limit
        if (win.getCounter() <= cfg.getLimit()) {
            return true;
        } else {
            // Exceeded the limit, revert the increment
            win.setCounter(win.getCounter() - 1);
            return false;
        }
    }

    @Override
    public void onUserConfigChange(String userId, UserRateLimit config) {
        state.remove(userId);
    }
}

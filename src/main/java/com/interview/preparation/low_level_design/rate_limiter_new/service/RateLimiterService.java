package com.interview.preparation.low_level_design.rate_limiter_new.service;

import com.interview.preparation.low_level_design.rate_limiter_new.FixedWindowStrategy;
import com.interview.preparation.low_level_design.rate_limiter_new.RateLimiterStrategy;
import com.interview.preparation.low_level_design.rate_limiter_new.SlidingWindowLogStrategy;
import com.interview.preparation.low_level_design.rate_limiter_new.TokenBucketStrategy;
import com.interview.preparation.low_level_design.rate_limiter_new.model.RateLimitAlgorithmEnum;
import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;
import com.interview.preparation.low_level_design.rate_limiter_new.repository.UserRateLimitRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Setter
@Service
public class RateLimiterService {
    private final UserRateLimitRepository userRateLimitRepository;
    private final Map<RateLimitAlgorithmEnum, RateLimiterStrategy> strategies = new EnumMap<>(RateLimitAlgorithmEnum.class);
    private volatile RateLimitAlgorithmEnum currentAlgorithm = RateLimitAlgorithmEnum.FIXED_WINDOW; // default

    public RateLimiterService(UserRateLimitRepository userRateLimitRepository,
                              FixedWindowStrategy fixed,
                              SlidingWindowLogStrategy sliding,
                              TokenBucketStrategy token) {
        this.userRateLimitRepository = userRateLimitRepository;
        strategies.put(RateLimitAlgorithmEnum.FIXED_WINDOW, fixed);
        strategies.put(RateLimitAlgorithmEnum.SLIDING_LOG, sliding);
        strategies.put(RateLimitAlgorithmEnum.TOKEN_BUCKET, token);
    }

    public void setAlgorithm(RateLimitAlgorithmEnum algo) {
        this.currentAlgorithm = algo;
    }

    public RateLimitAlgorithmEnum getAlgorithm() {
        return currentAlgorithm;
    }

    public void createOrUpdateUser(UserRateLimit user) {
        userRateLimitRepository.save(user);
        strategies.values().forEach(s -> s.onUserConfigChange(user.getUserId(), user));
    }

    public boolean allowRequest(String userId) {
        RateLimiterStrategy strategy = strategies.get(currentAlgorithm);
        return strategy.allowRequest(userId);
    }

    public List<UserRateLimit> listUsers() {
        return userRateLimitRepository.findAll();
    }

    public Optional<UserRateLimit> getUser(String userId) {
        return userRateLimitRepository.findById(userId);
    }
}

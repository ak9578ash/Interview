package com.interview.preparation.low_level_design.rate_limiter_new.repository;

import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;

import java.util.List;
import java.util.Optional;

public interface UserRateLimitRepository {
    Optional<UserRateLimit> findById(String userId);

    void save(UserRateLimit user);

    void update(UserRateLimit user);

    List<UserRateLimit> findAll();

    void delete(String userId);
}

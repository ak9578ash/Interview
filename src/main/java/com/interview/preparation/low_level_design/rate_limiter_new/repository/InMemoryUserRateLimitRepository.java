package com.interview.preparation.low_level_design.rate_limiter_new.repository;

import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryUserRateLimitRepository implements UserRateLimitRepository {
    private final ConcurrentMap<String, UserRateLimit> store = new ConcurrentHashMap<>();

    @Override
    public Optional<UserRateLimit> findById(String userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public void save(UserRateLimit userRateLimit) {
        store.put(userRateLimit.getUserId(), userRateLimit);
    }

    @Override
    public void update(UserRateLimit userRateLimit) {
        store.put(userRateLimit.getUserId(), userRateLimit);
    }

    @Override
    public List<UserRateLimit> findAll() {
        return store.values()
                .stream()
                .toList();
    }

    @Override
    public void delete(String userId) {
        store.remove(userId);
    }
}

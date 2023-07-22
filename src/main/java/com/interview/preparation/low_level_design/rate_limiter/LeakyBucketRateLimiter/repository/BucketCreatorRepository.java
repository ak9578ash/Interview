package com.interview.preparation.low_level_design.rate_limiter.LeakyBucketRateLimiter.repository;

import com.interview.preparation.low_level_design.rate_limiter.LeakyBucketRateLimiter.LeakyBucket;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class BucketCreatorRepository {
    Map<String , LeakyBucket> bucket = new HashMap<>();

    public LeakyBucket getBucketForUser(String userId) throws UserNotFoundException {
        LeakyBucket userBucket = bucket.get(userId);
        if (userBucket == null) {
            throw new UserNotFoundException("user does not exist");
        }
        return userBucket;
    }

    public LeakyBucket createUserBucket(String  userId, int bucketSize) {
        LeakyBucket userBucket = new LeakyBucket(bucketSize);
        bucket.put(userId, userBucket);
        return userBucket;
    }
}

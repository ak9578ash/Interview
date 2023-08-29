package com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter.repository;

import com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter.TokenBucket;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class BucketCreatorRepository {
    public static Map<String  , TokenBucket> bucket = new HashMap<>();

    public TokenBucket getUserBucket(String userId) throws UserNotFoundException {
        TokenBucket userBucket = bucket.get(userId);
        if(userBucket==null){
            throw new UserNotFoundException("user does not exist");
        }
        return userBucket;
    }

    public TokenBucket createUserBucket(String userId , long windowTime , long noOfRequest){
        TokenBucket userBucket = new TokenBucket(windowTime , noOfRequest);
        bucket.putIfAbsent(userId , userBucket);
        return userBucket;
    }

    public TokenBucket updateUserBucket(String userId , TokenBucket tokenBucket){
        bucket.put(userId , tokenBucket);
        return tokenBucket;
    }
}

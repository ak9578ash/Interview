package com.interview.preparation.low_level_design.rate_limiter.SlidingWindowCounterRateLimiter.Repository;

import com.interview.preparation.low_level_design.rate_limiter.SlidingLogRateLimiter.SlidingLog;
import com.interview.preparation.low_level_design.rate_limiter.SlidingWindowCounterRateLimiter.SlidingWindowCounter;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;
import jdk.dynalink.linker.support.SimpleLinkRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BucketCreatorRepository {
    Map<String , SlidingWindowCounter>bucket = new ConcurrentHashMap<>();

    public SlidingWindowCounter getUserBucket(String userId) throws UserNotFoundException {
        SlidingWindowCounter userBucket = bucket.get(userId);
        if(userBucket==null){
            throw new UserNotFoundException("user does not exist");
        }
        return userBucket;
    }

    public SlidingWindowCounter createUserBucket(String userId  , long windowTime , long noOfRequest ){
        SlidingWindowCounter userBucket = new SlidingWindowCounter(windowTime , noOfRequest);
        bucket.put(userId , userBucket);
        return userBucket;
    }

    public SlidingWindowCounter updateUserBucket(String userId , SlidingWindowCounter slidingWindowCounter){
        bucket.put(userId , slidingWindowCounter);
        return slidingWindowCounter;
    }
}

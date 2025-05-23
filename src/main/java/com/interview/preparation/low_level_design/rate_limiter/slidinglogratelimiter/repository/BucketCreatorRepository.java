package com.interview.preparation.low_level_design.rate_limiter.slidinglogratelimiter.repository;

import com.interview.preparation.low_level_design.rate_limiter.slidinglogratelimiter.SlidingLog;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class BucketCreatorRepository {
    Map<String , SlidingLog> bucket = new HashMap<>();

    public SlidingLog getUserBucket(String userId) throws UserNotFoundException {
        SlidingLog userBucket = bucket.get(userId);
        if(userBucket==null){
            throw new UserNotFoundException("user does not exist");
        }
        return userBucket;
    }

    public SlidingLog createUserBucket(String userId , long windowTime , long noOfRequest){
        SlidingLog userBucket = new SlidingLog(windowTime , noOfRequest);
        bucket.putIfAbsent(userId , userBucket);
        return userBucket;
    }

    public SlidingLog updateUserBucket(String userId , SlidingLog slidingLog){
        bucket.put(userId , slidingLog);
        return slidingLog;
    }
}

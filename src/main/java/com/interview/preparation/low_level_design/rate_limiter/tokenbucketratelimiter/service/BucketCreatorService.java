package com.interview.preparation.low_level_design.rate_limiter.tokenbucketratelimiter.service;


import com.interview.preparation.low_level_design.rate_limiter.tokenbucketratelimiter.TokenBucket;
import com.interview.preparation.low_level_design.rate_limiter.tokenbucketratelimiter.repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BucketCreatorService {
    private final BucketCreatorRepository bucketCreatorRepository;

    public BucketCreatorService(BucketCreatorRepository bucketCreatorRepository) {
        this.bucketCreatorRepository = bucketCreatorRepository;
    }

    public TokenBucket createUserBucket(String userId, long windowTime, long noOfRequest) {
        return bucketCreatorRepository.createUserBucket(userId, windowTime, noOfRequest);
    }

    public TokenBucket getUserBucket(String userId) throws UserNotFoundException {
        return bucketCreatorRepository.getUserBucket(userId);
    }

    public TokenBucket updateUserBucket(String userId , TokenBucket tokenBucket){
        return bucketCreatorRepository.updateUserBucket(userId , tokenBucket);
    }

    public void accessApplication(String userId) throws UserNotFoundException {
        TokenBucket userBucket = getUserBucket(userId);
        if (userBucket.grantAccess()) {
            log.info("{} able to access the application", userId);
        } else {
            log.info(" Too many request from {}, Please try after some time", userId);
        }
        /*
           we need to add optimistic concurrency control mechanism while updating to prevent race condition
           in distributed environment
        */
        updateUserBucket(userId , userBucket);
    }
}

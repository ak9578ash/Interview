package com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter.service;


import com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter.TokenBucket;
import com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter.repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;

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
            System.out.println(" able to access the application");
        } else {
            System.out.println(" Too many request, Please try after some time");
        }
        updateUserBucket(userId , userBucket);
    }
}

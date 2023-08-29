package com.interview.preparation.low_level_design.rate_limiter.SlidingWindowCounterRateLimiter.Service;

import com.interview.preparation.low_level_design.rate_limiter.SlidingWindowCounterRateLimiter.Repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.SlidingWindowCounterRateLimiter.SlidingWindowCounter;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;

public class BucketCreatorService {
    private final BucketCreatorRepository bucketCreatorRepository;

    public BucketCreatorService(BucketCreatorRepository bucketCreatorRepository) {
        this.bucketCreatorRepository = bucketCreatorRepository;
    }

    public SlidingWindowCounter createUserBucket(String userId, long windowTime, long noOfRequest) {
        return bucketCreatorRepository.createUserBucket(userId, windowTime, noOfRequest);
    }

    public SlidingWindowCounter getUserBucket(String userId) throws UserNotFoundException {
        return bucketCreatorRepository.getUserBucket(userId);
    }

    public SlidingWindowCounter updateUserBucket(String userId , SlidingWindowCounter slidingWindowCounter){
        return bucketCreatorRepository.updateUserBucket(userId , slidingWindowCounter);
    }

    public void accessApplication(String userId) throws UserNotFoundException {
        SlidingWindowCounter userBucket = getUserBucket(userId);
        if (userBucket.grantAccess()) {
            System.out.println(" able to access the application");
        } else {
            System.out.println(" Too many request, Please try after some time");
        }
        updateUserBucket(userId , userBucket);
    }
}

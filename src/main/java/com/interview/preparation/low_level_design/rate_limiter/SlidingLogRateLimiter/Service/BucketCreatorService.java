package com.interview.preparation.low_level_design.rate_limiter.SlidingLogRateLimiter.Service;

import com.interview.preparation.low_level_design.rate_limiter.SlidingLogRateLimiter.Repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.SlidingLogRateLimiter.SlidingLog;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;

public class BucketCreatorService {
    private final BucketCreatorRepository bucketCreatorRepository;

    public BucketCreatorService(BucketCreatorRepository bucketCreatorRepository) {
        this.bucketCreatorRepository = bucketCreatorRepository;
    }

    public SlidingLog createUserBucket(String userId, long windowTime, long noOfRequest) {
        return bucketCreatorRepository.createUserBucket(userId, windowTime, noOfRequest);
    }

    public SlidingLog getUserBucket(String userId) throws UserNotFoundException {
        return bucketCreatorRepository.getUserBucket(userId);
    }

    public SlidingLog updateUserBucket(String userId , SlidingLog slidingLog){
        return bucketCreatorRepository.updateUserBucket(userId , slidingLog);
    }

    public void accessApplication(String userId) throws UserNotFoundException {
        SlidingLog userBucket = getUserBucket(userId);
        if (userBucket.grantAccess()) {
            System.out.println(" able to access the application");
        } else {
            System.out.println(" Too many request, Please try after some time");
        }
        updateUserBucket(userId , userBucket);

    }
}

package com.interview.preparation.low_level_design.rate_limiter.slidingwindowcounterratelimiter.service;

import com.interview.preparation.low_level_design.rate_limiter.slidingwindowcounterratelimiter.repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.slidingwindowcounterratelimiter.SlidingWindowCounter;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BucketCreatorService {
    private final BucketCreatorRepository bucketCreatorRepository;

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
            log.info(" able to access the application");
        } else {
            log.info(" Too many request, Please try after some time");
        }
        updateUserBucket(userId , userBucket);
    }
}

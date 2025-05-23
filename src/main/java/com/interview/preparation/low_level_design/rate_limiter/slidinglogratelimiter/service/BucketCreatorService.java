package com.interview.preparation.low_level_design.rate_limiter.slidinglogratelimiter.service;

import com.interview.preparation.low_level_design.rate_limiter.slidinglogratelimiter.repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.slidinglogratelimiter.SlidingLog;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BucketCreatorService {
    private final BucketCreatorRepository bucketCreatorRepository;

    public SlidingLog createUserBucket(String userId, long windowTime, long noOfRequest) {
        return bucketCreatorRepository.createUserBucket(userId, windowTime, noOfRequest);
    }

    public SlidingLog getUserBucket(String userId) throws UserNotFoundException {
        return bucketCreatorRepository.getUserBucket(userId);
    }

    public SlidingLog updateUserBucket(String userId , SlidingLog slidingLog) {
        return bucketCreatorRepository.updateUserBucket(userId , slidingLog);
    }

    public void accessApplication(String userId) throws UserNotFoundException {
        // we should also fetch rule config from rule cache but in this case we have hard coded those config
        SlidingLog userBucket = getUserBucket(userId);
        if (userBucket.grantAccess()) {
            log.info("able to access the application");
        } else {
            log.info("Too many request, Please try after some time");
        }
        /*
           we need to add optimistic concurrency control mechanism while updating to prevent race condition
           in distributed environment
        */
        updateUserBucket(userId , userBucket);
    }
}

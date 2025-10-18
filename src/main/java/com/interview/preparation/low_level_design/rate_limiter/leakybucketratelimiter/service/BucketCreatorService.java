package com.interview.preparation.low_level_design.rate_limiter.leakybucketratelimiter.service;

import com.interview.preparation.low_level_design.rate_limiter.leakybucketratelimiter.LeakyBucket;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.rate_limiter.leakybucketratelimiter.repository.BucketCreatorRepository;
import org.springframework.stereotype.Service;

@Service
public class BucketCreatorService {
    private final BucketCreatorRepository bucketCreatorRepository;

    public BucketCreatorService(BucketCreatorRepository bucketCreatorRepository) {
        this.bucketCreatorRepository = bucketCreatorRepository;
    }

    public LeakyBucket getBucketForUser(String  userId) throws UserNotFoundException {
       return bucketCreatorRepository.getBucketForUser(userId);
    }

    public LeakyBucket createUserBucket(String userId , int bucketSize){
       return bucketCreatorRepository.createUserBucket(userId , bucketSize);
    }

    public void accessApplication(String  userId) throws UserNotFoundException {
        LeakyBucket userBucket = getBucketForUser(userId);
        if(userBucket.grantAccess()){
            System.out.println(" able to access the application");
        }else{
            System.out.println(" Too many request, Please try after some time");
        }
    }
}

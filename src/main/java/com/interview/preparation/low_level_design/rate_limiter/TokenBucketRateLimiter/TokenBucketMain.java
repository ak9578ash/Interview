package com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter;

import com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter.repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.TokenBucketRateLimiter.service.BucketCreatorService;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.rate_limiter.model.User;
import com.interview.preparation.low_level_design.rate_limiter.model.UserProfile;

import java.util.concurrent.TimeUnit;

public class TokenBucketMain {
    public static BucketCreatorRepository bucketCreatorRepository;
    public static BucketCreatorService bucketCreatorService;

    public static void main(String[] args) throws InterruptedException, UserNotFoundException {
        bucketCreatorRepository = new BucketCreatorRepository();
        bucketCreatorService = new BucketCreatorService(bucketCreatorRepository);

        User user1 = new User(new UserProfile("akashgupta9578@gmail.com","akash","gupta"));
        bucketCreatorService.createUserBucket(user1.getId(), 1 ,4);

        bucketCreatorService.accessApplication(user1.getId());
        bucketCreatorService.accessApplication(user1.getId());
        bucketCreatorService.accessApplication(user1.getId());
        bucketCreatorService.accessApplication(user1.getId());

        TimeUnit.SECONDS.sleep(1);

        bucketCreatorService.accessApplication(user1.getId());

        User user2 = new User(new UserProfile("xyz@gmail.com","xyz","gupta"));
        bucketCreatorService.createUserBucket(user2.getId(), 2 ,4);

        bucketCreatorService.accessApplication(user2.getId());
        bucketCreatorService.accessApplication(user2.getId());
        bucketCreatorService.accessApplication(user2.getId());
        bucketCreatorService.accessApplication(user2.getId());
    }
}

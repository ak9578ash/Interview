package com.interview.preparation.low_level_design.rate_limiter.slidingwindowcounterratelimiter;

import com.interview.preparation.low_level_design.rate_limiter.slidingwindowcounterratelimiter.repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.slidingwindowcounterratelimiter.service.BucketCreatorService;
import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.rate_limiter.model.User;
import com.interview.preparation.low_level_design.rate_limiter.model.UserProfile;

public class SlidingWindowCounterMain {
    public static BucketCreatorRepository bucketCreatorRepository;
    public static BucketCreatorService bucketCreatorService;

    public static void main(String[] args) throws UserNotFoundException {
        bucketCreatorRepository = new BucketCreatorRepository();
        bucketCreatorService = new BucketCreatorService(bucketCreatorRepository);

        User user1 = new User(new UserProfile("akashgupta9578@gmail.com","akash","gupta"));
        bucketCreatorService.createUserBucket(user1.getId(), 1 ,3);

        bucketCreatorService.accessApplication(user1.getId());
        bucketCreatorService.accessApplication(user1.getId());
        bucketCreatorService.accessApplication(user1.getId());
        bucketCreatorService.accessApplication(user1.getId());
    }
}

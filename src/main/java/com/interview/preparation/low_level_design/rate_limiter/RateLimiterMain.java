package com.interview.preparation.low_level_design.rate_limiter;

import com.interview.preparation.low_level_design.rate_limiter.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.rate_limiter.LeakyBucketRateLimiter.repository.BucketCreatorRepository;
import com.interview.preparation.low_level_design.rate_limiter.LeakyBucketRateLimiter.service.BucketCreatorService;
import com.interview.preparation.low_level_design.rate_limiter.model.User;
import com.interview.preparation.low_level_design.rate_limiter.model.UserProfile;

import java.util.UUID;

public class RateLimiterMain {
    private static BucketCreatorRepository  bucketCreatorRepository ;
    private static BucketCreatorService bucketCreatorService;

    public static void main(String[] args) throws UserNotFoundException {
//        bucketCreatorRepository = new BucketCreatorRepository();
//        bucketCreatorService = new BucketCreatorService(bucketCreatorRepository);
//
//        User user1 = new User(new UserProfile("akashgupta9578@gmail.com","akash","gupta"));
//        bucketCreatorService.createUserBucket(user1.getId(), 3);
//
//       try{
//           bucketCreatorService.accessApplication(user1.getId());
//           bucketCreatorService.accessApplication(user1.getId());
//
//           bucketCreatorService.accessApplication(UUID.randomUUID().toString());
//
//           bucketCreatorService.accessApplication(user1.getId());
//       }catch (Exception e){
//           System.out.println(e.getMessage());
//       }
    }
}

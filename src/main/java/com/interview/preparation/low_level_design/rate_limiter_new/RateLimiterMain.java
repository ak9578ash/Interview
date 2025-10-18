package com.interview.preparation.low_level_design.rate_limiter_new;

import com.interview.preparation.low_level_design.rate_limiter_new.model.RateLimitAlgorithmEnum;
import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;
import com.interview.preparation.low_level_design.rate_limiter_new.repository.InMemoryUserRateLimitRepository;
import com.interview.preparation.low_level_design.rate_limiter_new.repository.UserRateLimitRepository;
import com.interview.preparation.low_level_design.rate_limiter_new.service.RateLimiterService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RateLimiterMain {
    public static UserRateLimitRepository userRateLimitRepository = new InMemoryUserRateLimitRepository();
    public static FixedWindowStrategy fixedWindowStrategy = new FixedWindowStrategy(userRateLimitRepository);
    public static SlidingWindowLogStrategy slidingWindowStrategy = new SlidingWindowLogStrategy(userRateLimitRepository);
    public static TokenBucketStrategy tokenBucketStrategy = new TokenBucketStrategy(userRateLimitRepository);
    public static RateLimiterService rateLimiterService =
            new RateLimiterService(userRateLimitRepository, fixedWindowStrategy, slidingWindowStrategy, tokenBucketStrategy);

    public static void main(String[] args) throws InterruptedException {
        UserRateLimit user_1 = new UserRateLimit("user_1", 5, 60, 5);
        UserRateLimit user_2 = new UserRateLimit("user_2", 2, 60, 10);
        rateLimiterService.createOrUpdateUser(user_1);
        rateLimiterService.createOrUpdateUser(user_2);

        rateLimiterService.setAlgorithm(RateLimitAlgorithmEnum.TOKEN_BUCKET);

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }

        Thread.sleep(60000);

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }

        if (rateLimiterService.allowRequest("user_1")) {
            log.info("Request allowed for user_1");
        } else {
            log.error("Request denied for user_1");
        }



//        Thread thread1 = Thread.ofVirtual()
//                .unstarted(() -> {
//                    if (rateLimiterService.allowRequest("user_1")) {
//                        log.info("Request allowed for user_1");
//                    } else {
//                        log.error("Request denied for user_1");
//                    }
//                });
//
//        Thread thread2 = Thread.ofVirtual()
//                .unstarted(() -> {
//                    if (rateLimiterService.allowRequest("user_1")) {
//                        log.info("Request allowed for user_1");
//                    } else {
//                        log.error("Request denied for user_1");
//                    }
//                });
//
//        Thread thread3 = Thread.ofVirtual()
//                .unstarted(() -> {
//                    if (rateLimiterService.allowRequest("user_1")) {
//                        log.info("Request allowed for user_1");
//                    } else {
//                        log.error("Request denied for user_1");
//                    }
//                });
//
//        Thread thread4 = Thread.ofVirtual()
//                .unstarted(() -> {
//                    if (rateLimiterService.allowRequest("user_1")) {
//                        log.info("Request allowed for user_1");
//                    } else {
//                        log.error("Request denied for user_1");
//                    }
//                });
//
//        Thread thread5 = Thread.ofVirtual()
//                .unstarted(() -> {
//                    if (rateLimiterService.allowRequest("user_1")) {
//                        log.info("Request allowed for user_1");
//                    } else {
//                        log.error("Request denied for user_1");
//                    }
//                });
//
//        Thread thread6 = Thread.ofVirtual()
//                .unstarted(() -> {
//                    if (rateLimiterService.allowRequest("user_1")) {
//                        log.info("Request allowed for user_1");
//                    } else {
//                        log.error("Request denied for user_1");
//                    }
//                });
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
//        thread6.start();
//
//        try {
//            thread1.join();
//            thread2.join();
//            thread3.join();
//            thread4.join();
//            thread5.join();
//            thread6.join();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//        Thread.sleep(60000); // wait for 60 seconds to reset the window
//
//        if (rateLimiterService.allowRequest("user_1")) {
//            log.info("Request allowed for user_1");
//        } else {
//            log.error("Request denied for user_1");
//        }
    }
}

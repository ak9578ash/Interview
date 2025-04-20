package com.interview.preparation.low_level_design.rate_limiter.leakybucketratelimiter;

import com.interview.preparation.low_level_design.rate_limiter.RateLimiter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LeakyBucket implements RateLimiter {
    private final BlockingQueue<Integer> queue;

    public LeakyBucket(int bucketSize) {
        this.queue = new LinkedBlockingDeque<>(bucketSize);
    }

    @Override
    public boolean grantAccess() {
        if (this.queue.remainingCapacity() > 0) {
            queue.add(1);
            return true;
        }
        return false;
    }
}

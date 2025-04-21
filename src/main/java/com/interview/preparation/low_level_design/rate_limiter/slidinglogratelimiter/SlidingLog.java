package com.interview.preparation.low_level_design.rate_limiter.slidinglogratelimiter;

import com.interview.preparation.low_level_design.rate_limiter.RateLimiter;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * windowTime and noOfRequest will be stored in rule cache like Redis
 * slidingWindow will be stored in counter cache like Redis in the form of sorted set
 */
public class SlidingLog implements RateLimiter {
    private final Queue<Long> slidingWindow;
    // in seconds
    private final long windowTime;
    private final long noOfRequest;

    public SlidingLog(long windowTime , long noOfRequest){
        this.slidingWindow = new ConcurrentLinkedDeque<>();
        this.windowTime = windowTime;
        this.noOfRequest = noOfRequest;
    }
    @Override
    public boolean grantAccess() {
        Long currentTimeOfRequest = System.currentTimeMillis();
        updateSlidingWindow(currentTimeOfRequest);
        if(slidingWindow.size() + 1 <= noOfRequest){
            slidingWindow.offer(currentTimeOfRequest);
            return true;
        }
        return false;
    }

    private void updateSlidingWindow(Long currentTimeOfRequest){
        if(slidingWindow.isEmpty()){
            return ;
        }
        while(!slidingWindow.isEmpty()){
            long slidingWindowGap = (currentTimeOfRequest - slidingWindow.peek())/1000;
            if(slidingWindowGap > windowTime){
                slidingWindow.poll();
            }else{
                break;
            }
        }
    }
}

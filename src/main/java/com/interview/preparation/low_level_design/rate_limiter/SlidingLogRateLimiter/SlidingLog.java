package com.interview.preparation.low_level_design.rate_limiter.SlidingLogRateLimiter;

import com.interview.preparation.low_level_design.rate_limiter.RateLimiter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class SlidingLog implements RateLimiter {
    private Queue<Long> slidingWindow;
    private long windowTime; // in seconds;
    private long noOfRequest;

    public SlidingLog(long windowTime , long noOfRequest){
        this.slidingWindow = new ConcurrentLinkedDeque<>();
        this.windowTime = windowTime;
        this.noOfRequest = noOfRequest;
    }
    @Override
    public boolean grantAccess() {
        Long currentTimeOfRequest = System.currentTimeMillis();
        updateSlidingWindow(currentTimeOfRequest);
        if(slidingWindow.size() < noOfRequest){
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

package com.interview.preparation.low_level_design.rate_limiter.SlidingWindowCounterRateLimiter;

import ch.qos.logback.core.model.INamedModel;
import com.interview.preparation.low_level_design.rate_limiter.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounter implements RateLimiter {
    private Map<Long, Integer> slidingWindowCounter;
    private long windowTime; // in seconds;
    private long  noOfRequest;

    public SlidingWindowCounter(Long windowTime , Long noOfRequest){
       this.windowTime = windowTime;
       this.noOfRequest = noOfRequest;
       this.slidingWindowCounter = new ConcurrentHashMap<>();
    }
    @Override
    public boolean grantAccess() {
        Long currentRequestCountInWindowTime = 0L;
        Long currentTimeOfRequest = System.currentTimeMillis();

        currentRequestCountInWindowTime = getRequestCountInWindowTime(currentTimeOfRequest , currentRequestCountInWindowTime);
        if(currentRequestCountInWindowTime < noOfRequest){

            Integer requestCount = 0;
            if(slidingWindowCounter.containsKey(currentTimeOfRequest)){
                requestCount = slidingWindowCounter.get(currentTimeOfRequest);
            }
            slidingWindowCounter.putIfAbsent(currentTimeOfRequest , 1 + requestCount);
            return true;
        }
        return false;
    }
    private Long getRequestCountInWindowTime(long currentTimeOfRequest , long currentRequestCountInWindowTime){
        if(slidingWindowCounter.isEmpty()){
            return 0L;
        }
        for(long timeStamp : slidingWindowCounter.keySet()){
            long slidingWindowGap = (currentTimeOfRequest - timeStamp)/1000;
            if(slidingWindowGap > windowTime){
                continue;
            }else{
                currentRequestCountInWindowTime = currentRequestCountInWindowTime + slidingWindowCounter.get(timeStamp);
            }
        }
        return currentRequestCountInWindowTime;
    }
}

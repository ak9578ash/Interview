package com.interview.preparation.low_level_design.interview.rate_limiter_proxy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RateLimiterProxy {
  private final Queue<Request> secondsRequests;
  private final Queue<Request> tenSecondsRequests;
  private final Queue<Request> minuteRequests;

  public RateLimiterProxy() {
    this.secondsRequests = new ConcurrentLinkedQueue<>();
    this.tenSecondsRequests = new ConcurrentLinkedQueue<>();
    this.minuteRequests = new ConcurrentLinkedQueue<>();
  }

  public boolean grantAccess(Request request) {
    cleanupQueue(secondsRequests, request.getCreatedAt(), Duration.ofSeconds(1));
    cleanupQueue(tenSecondsRequests, request.getCreatedAt(), Duration.ofSeconds(10));
    cleanupQueue(minuteRequests, request.getCreatedAt(), Duration.ofMinutes(1));

    if (secondsRequests.size() < 3 && tenSecondsRequests.size() < 20 && minuteRequests.size() < 20) {
      secondsRequests.offer(request);
      tenSecondsRequests.offer(request);
      minuteRequests.offer(request);
      return true;
    }
    return false;
  }

  private synchronized void cleanupQueue(Queue<Request> queue, LocalDateTime requestTime, Duration window) {
    while (!queue.isEmpty()) {
      if (Duration.between(queue.peek().getCreatedAt(), requestTime).compareTo(window) > 0) {
        queue.poll();
      } else {
        break;
      }
    }
  }
}

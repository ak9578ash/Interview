package com.interview.preparation.multi_threading.count_down_latch.example2;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

/**
 * Problem Requirement: <a href="https://www.baeldung.com/java-countdown-latch#a-pool-of-threads-waiting-to-begin">...</a>
 */
@Slf4j
public class Demo {
  public static void main(String[] args) {
    CountDownLatch readyThreadCounter = new CountDownLatch(3);
    CountDownLatch callingThreadBlocker = new CountDownLatch(1);
    CountDownLatch completedThreadCounter = new CountDownLatch(3);

    Thread th1 = Thread.ofPlatform()
        .unstarted(new Worker(readyThreadCounter, callingThreadBlocker, completedThreadCounter));

    Thread th2 = Thread.ofPlatform()
        .unstarted(new Worker(readyThreadCounter, callingThreadBlocker, completedThreadCounter));

    Thread th3 = Thread.ofPlatform()
        .unstarted(new Worker(readyThreadCounter, callingThreadBlocker, completedThreadCounter));

    try {
      th1.start();
      th2.start();
      th3.start();

      readyThreadCounter.await();
      log.info("All worker are ready to work");
      callingThreadBlocker.countDown();
      completedThreadCounter.await();
      log.info("All worker are completed");

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}

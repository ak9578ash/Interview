package com.interview.preparation.multi_threading.count_down_latch.example2;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Worker implements Runnable {
  private final CountDownLatch readyThreadCounter;
  private final CountDownLatch callingThreadBlocker;
  private final CountDownLatch completedThreadCounter;

  public Worker(CountDownLatch readyThreadCounter, CountDownLatch callingThreadBlocker,
                CountDownLatch completedThreadCounter) {
    this.readyThreadCounter = readyThreadCounter;
    this.callingThreadBlocker = callingThreadBlocker;
    this.completedThreadCounter = completedThreadCounter;
  }

  @Override
  public void run() {
    readyThreadCounter.countDown();
    try {
      callingThreadBlocker.await();
      Thread.sleep(2000); // to mimic some processing
      log.info("Worker is executed");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      completedThreadCounter.countDown();
    }
  }
}

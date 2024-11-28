package com.interview.preparation.multi_threading.count_down_latch.example1;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Worker implements Runnable {
  private final CountDownLatch countDownLatch;

  public Worker(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(10000); // To mimic some heavy processing
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      log.info("Counted Down");
      countDownLatch.countDown();
    }
  }
}

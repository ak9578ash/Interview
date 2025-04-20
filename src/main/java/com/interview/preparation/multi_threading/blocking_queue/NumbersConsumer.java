package com.interview.preparation.multi_threading.blocking_queue;

import java.util.concurrent.BlockingQueue;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class NumbersConsumer implements Runnable {
  private final BlockingQueue<Integer> numberQueue;

  public NumbersConsumer(BlockingQueue<Integer> numberQueue) {
    this.numberQueue = numberQueue;
  }

  public void run() {
    try {
      int num = 5;
      while (num > 0) {
        numberQueue.take();
        log.info(Thread.currentThread().getName() + " is consuming");
        num--;
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}

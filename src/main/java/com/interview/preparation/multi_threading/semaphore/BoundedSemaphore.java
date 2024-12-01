package com.interview.preparation.multi_threading.semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoundedSemaphore {
  private final int bound;
  private int signals;


  public BoundedSemaphore(int upperBound) {
    this.bound = upperBound;
    this.signals = 0;
  }

  public synchronized void take() throws InterruptedException {
    while (this.signals == bound) {
      log.info("Thread is waiting to acquire semaphore");
      this.wait();
    }
    log.info("Semaphore is acquired");
    this.signals++;
  }

  public synchronized void release() throws IllegalStateException {
    if (this.signals == 0) {
      throw new IllegalStateException("No signal to release");
    }

    log.info("Semaphore is released");
    this.signals--;
    this.notifyAll();
  }
}
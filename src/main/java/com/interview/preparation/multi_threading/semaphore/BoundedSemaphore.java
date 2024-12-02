package com.interview.preparation.multi_threading.semaphore;

import lombok.extern.slf4j.Slf4j;

/**
 * BoundedSemaphore is a semaphore that has an upper bound on the number of permits it can issue or the number of threads that can access a resource at a time.
 */
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
    this.notifyAll();
  }

  public synchronized void release() throws InterruptedException {
    while (this.signals == 0) {
      log.info("Thread is waiting to release semaphore");
      this.wait();
    }

    log.info("Semaphore is released");
    this.signals--;
    this.notifyAll();
  }
}
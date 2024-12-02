package com.interview.preparation.multi_threading.mutex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomMutex {
  private final int bound;
  private int signals;
  private Thread lockedBy;

  public CustomMutex() {
    this.bound = 1;
    this.signals = 0;
    this.lockedBy = null;
  }

  public synchronized void take() throws InterruptedException {
    while (this.signals == bound) {
      log.info("Thread is waiting to acquire mutex");
      this.wait();
    }
    log.info("Mutex is acquired");
    this.signals++;
    this.lockedBy = Thread.currentThread();
  }

  public synchronized void release() throws IllegalStateException {
    Thread callingThread = Thread.currentThread();
    if (lockedBy != callingThread) { // here we are checking the thread ownership
      throw new IllegalStateException("Calling thread has not acquired the mutex");
    }

    if (this.signals == 0) {
      throw new IllegalStateException("No mutex to release");
    }

    log.info("Mutex is released");
    this.signals--;
    this.lockedBy = null;
    this.notifyAll();
  }
}

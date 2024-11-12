package com.interview.preparation.multi_threading.deadlock.prevention.timeout_backoff;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Runnable1 implements Runnable {
  private final Lock lock1;
  private final Lock lock2;

  public Runnable1(Lock lock1, Lock lock2) {
    this.lock1 = lock1;
    this.lock2 = lock2;
  }

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();
    while (true) {
      while (!isBothLocksAcquired()) {
        System.err.println(threadName + ": " + "Failed to acquire both LOCKS.");
        System.out.println(threadName + ": " + "RETRYING...");

        try {
          Thread.sleep(100L * (long) Math.random());
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }

      System.out.println(threadName + ": " + "Both LOCKS acquired.");
      // do the the stuff

      lock2.unlock();
      lock1.unlock();
    }
  }

  private boolean isBothLocksAcquired() {
    boolean isLock1Acquired;
    try {
      isLock1Acquired = lock1.tryLock(1000, TimeUnit.MILLISECONDS);
      if (!isLock1Acquired) {
        return false;
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return false;
    }

    boolean isLock2Acquired;
    try {
      isLock2Acquired = lock2.tryLock(1000, TimeUnit.MILLISECONDS);
      if (!isLock2Acquired) {
        lock1.unlock();
        return false;
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      lock1.unlock();
      return false;
    }

    return true;
  }
}

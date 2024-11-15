package com.interview.preparation.multi_threading.starvation_and_fairness.example2_without_synchronized;

public class CustomLock {
  private boolean isLocked = false;
  private Thread lockingThread = null;

  public synchronized void lock() throws InterruptedException {
    while (isLocked) {
      this.wait();
    }
    isLocked = true;
    lockingThread = Thread.currentThread();
  }

  public synchronized void unlock() {
    if (lockingThread != Thread.currentThread()) {
      System.err.println("Calling thread has not locked this lock");
      throw new IllegalArgumentException();
    }
    isLocked = false;
    lockingThread = null;
    this.notifyAll();
  }
}

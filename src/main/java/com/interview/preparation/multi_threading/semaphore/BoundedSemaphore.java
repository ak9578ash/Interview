package com.interview.preparation.multi_threading.semaphore;

public class BoundedSemaphore {
  private int signals;
  private final int bound;
  private  Thread lockedByThread;

  public BoundedSemaphore(int upperBound){
    this.bound = upperBound;
    this.signals = 0;
    this.lockedByThread = null;
  }

  public synchronized void take() throws InterruptedException{
    while(this.signals == bound) {
      wait();
    }
    this.signals++;
    lockedByThread = Thread.currentThread();
    this.notifyAll();
  }

  public synchronized void release() throws InterruptedException{
    Thread callingThread = Thread.currentThread();
    if (callingThread != lockedByThread) {
      throw new IllegalStateException("Calling thread has not locked this semaphore");
    }
    while(this.signals == 0) {
      wait();
    }
    this.signals--;
    lockedByThread = null;
    this.notifyAll();
  }
}
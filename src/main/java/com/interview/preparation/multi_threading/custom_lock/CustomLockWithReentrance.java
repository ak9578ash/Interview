package com.interview.preparation.multi_threading.custom_lock;

public class CustomLockWithReentrance {
  private boolean isLocked;
  private Thread lockedBy;
  private int lockedCount = 0;

  public CustomLockWithReentrance() {
    this.isLocked = false;
    this.lockedBy = null;
    this.lockedCount = 0;
  }

  public synchronized void lock() {
    Thread currentThread = Thread.currentThread();
    while (isLocked && lockedBy != currentThread) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }
    }
    isLocked = true;
    lockedBy = currentThread;
    lockedCount++;
  }

  public synchronized void unlock() {
    Thread currentThread = Thread.currentThread();
    if (lockedBy != currentThread) {
      throw new IllegalStateException("Calling thread has not locked this lock");
    }

    lockedCount--;
    if (lockedCount == 0) {
      isLocked = false;
      lockedBy = null;
      notifyAll();
    }
  }
}

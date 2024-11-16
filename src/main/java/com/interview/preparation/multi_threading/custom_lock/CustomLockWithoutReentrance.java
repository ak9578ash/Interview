package com.interview.preparation.multi_threading.custom_lock;

/**
 * lock() -> 1. true --> isLocked = true
 *           2. false --> wait() , always in while loop for spurious wake_ups
 *
 * unlock() -> 1. true --> isLocked = false , notifyAll()
 */
public class CustomLockWithoutReentrance {
  private boolean isLocked;

  public CustomLockWithoutReentrance() {
    this.isLocked = false;
  }

  public synchronized void lock() {
    while(isLocked) {
      try{
        this.wait();
      }catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    isLocked = true;
  }

  public synchronized void unlock() {
    isLocked = false;
    notifyAll();
  }
}
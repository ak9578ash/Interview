package com.interview.preparation.multi_threading.starvation_and_fairness.example2_without_synchronized;

public class Synchronizer {
  private CustomLock customLock;
  public Synchronizer(){
    this.customLock = new CustomLock();
  }

  public void doSynchronized() throws InterruptedException{
    customLock.lock();
    // do the work here
    customLock.unlock();
  }
}
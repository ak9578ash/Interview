package com.interview.preparation.multi_threading.synchronization.example1;

public class Counter {
  private Integer count = 0;

  public synchronized void incrementCounter() {
    for (int i = 0; i < 1_000_000; i++) {
      this.count++;
    }
  }

  public  Integer getCounter() {
    return this.count;
  }
}

package com.interview.preparation.multi_threading.semaphore;

public class Counter {
  private Integer count = 0;

  public  void incrementCounter() {
    for (int i = 0; i < 1_000_000; i++) {
      this.count++;
    }
  }

  public  Integer getCounter() {
    return this.count;
  }
}
